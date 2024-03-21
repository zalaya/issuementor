package org.backend.issuementor.controllers;

import org.backend.issuementor.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> findAll(@RequestParam("identification") String identification, @RequestHeader("token") String token) {
        ResponseEntity<?> response = userService.findAll(identification, token);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }
}
