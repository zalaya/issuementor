package org.backend.issuementor.controllers;

import org.backend.issuementor.dtos.LoginRequestDTO;
import org.backend.issuementor.dtos.SignupRequestDTO;
import org.backend.issuementor.models.User;
import org.backend.issuementor.services.JWTService;
import org.backend.issuementor.services.MapperService;
import org.backend.issuementor.services.PasswordService;
import org.backend.issuementor.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MapperService mapperService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO signupRequest) {
        if (userService.existsByEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        signupRequest.setCreationDate(Timestamp.from(Instant.now()));
        userService.saveEncoded(mapperService.map(signupRequest, User.class));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        Optional<User> databaseUser = userService.findByEmail(loginRequest.getEmail());

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!passwordService.validate(loginRequest.getPassword(), databaseUser.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        databaseUser.get().setLoginDate(Timestamp.from(Instant.now()));
        userService.saveUnencoded(databaseUser.get());

        return new ResponseEntity<>(jwtService.generate(databaseUser.get().getId()), HttpStatus.OK);
    }
}
