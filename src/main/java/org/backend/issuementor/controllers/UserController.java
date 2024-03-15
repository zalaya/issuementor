package org.backend.issuementor.controllers;

import org.backend.issuementor.dtos.CredentialsDTO;
import org.backend.issuementor.dtos.UserDTO;
import org.backend.issuementor.mappers.UserMapper;
import org.backend.issuementor.models.User;
import org.backend.issuementor.services.JWTService;
import org.backend.issuementor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.saveEncoded(user);

        return new ResponseEntity<>(UserMapper.toUserDTO(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO credentials) {
        Optional<User> databaseUser = userService.findByEmail(credentials.getEmail());

        if (databaseUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!passwordEncoder.matches(credentials.getPassword(), databaseUser.get().getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(jwtService.generate(databaseUser.get().getId()), HttpStatus.OK);
    }
}
