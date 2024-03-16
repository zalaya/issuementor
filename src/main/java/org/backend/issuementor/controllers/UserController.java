package org.backend.issuementor.controllers;

import org.backend.issuementor.dtos.CredentialsDTO;
import org.backend.issuementor.dtos.TokenDTO;
import org.backend.issuementor.dtos.AdvancedUserDTO;
import org.backend.issuementor.dtos.BasicUserDTO;
import org.backend.issuementor.models.User;
import org.backend.issuementor.services.JWTService;
import org.backend.issuementor.services.MapperService;
import org.backend.issuementor.services.PasswordService;
import org.backend.issuementor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> signup(@RequestBody AdvancedUserDTO request) {
        if (userService.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        request.setCreationDate(Timestamp.from(Instant.now()));
        User user = userService.saveEncoded(mapperService.map(request, User.class));

        return new ResponseEntity<>(mapperService.map(user, BasicUserDTO.class), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO request) {
        Optional<User> databaseUser = userService.findByEmail(request.getEmail());

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!passwordService.validate(request.getPassword(), databaseUser.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        databaseUser.get().setLoginDate(Timestamp.from(Instant.now()));
        userService.saveUnencoded(databaseUser.get());

        return new ResponseEntity<>(new TokenDTO(
            jwtService.generate(databaseUser.get().getId()),
            jwtService.getExpiration()
        ), HttpStatus.OK);
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?> delete(@PathVariable String email) {
        Optional<User> databaseUser = userService.findByEmail(email);

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.delete(databaseUser.get());

        return new ResponseEntity<>(
            mapperService.map(databaseUser.get(), BasicUserDTO.class),
            HttpStatus.OK
        );
    }
}
