package org.backend.issuementor.controllers;

import org.backend.issuementor.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AbstractController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean match(User user, User databaseUser) {
        return passwordEncoder.matches(user.getPassword(), databaseUser.getPassword());
    }
}
