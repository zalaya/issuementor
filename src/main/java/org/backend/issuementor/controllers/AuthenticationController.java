package org.backend.issuementor.controllers;

import org.backend.issuementor.dtos.LoginRequestDTO;
import org.backend.issuementor.dtos.LoginResponseDTO;
import org.backend.issuementor.dtos.SignupRequestDTO;
import org.backend.issuementor.dtos.SignupResponseDTO;
import org.backend.issuementor.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO request) {
        ResponseEntity<?> response = authenticationService.signup(request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>((SignupResponseDTO) response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        ResponseEntity<?> response = authenticationService.login(request);

        if (response.getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>((LoginResponseDTO) response.getBody(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.valueOf(response.getStatusCode().value()));
    }
}
