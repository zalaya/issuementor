package org.backend.jauth.controllers;

import org.backend.jauth.dtos.CredentialsDTO;
import org.backend.jauth.dtos.TokenDTO;
import org.backend.jauth.dtos.UnsafeUserDTO;
import org.backend.jauth.dtos.BasicSafeUserDTO;
import org.backend.jauth.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UnsafeUserDTO request) {
        ResponseEntity<?> response = authenticationService.signup(request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>((BasicSafeUserDTO) response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialsDTO request) {
        ResponseEntity<?> response = authenticationService.login(request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>((TokenDTO) response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam("email") String email, @RequestParam("token") String token) {
        ResponseEntity<?> response = authenticationService.verify(email, token);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }
}
