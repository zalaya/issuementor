package org.backend.issuementor.controllers;

import org.backend.issuementor.models.User;
import org.backend.issuementor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(
                "An account with this email already exists.",
                HttpStatus.CONFLICT
            );
        }

        user.setPassword(
            encode(user.getPassword())
        );

        return new ResponseEntity<>(
            "The account has been successfully created.",
            HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> databaseUser = userService.findByEmail(user.getEmail());

        if (databaseUser.isEmpty() || match(user, databaseUser.get())) {
            return new ResponseEntity<>(
                "The account credentials are not valid.",
                HttpStatus.UNAUTHORIZED
            );
        }

        return new ResponseEntity<>(
            databaseUser.get(),
            HttpStatus.OK
        );
    }
}
