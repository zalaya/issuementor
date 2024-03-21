package org.backend.issuementor.services;

import org.backend.issuementor.dtos.LoginRequestDTO;
import org.backend.issuementor.dtos.SignupRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signup(SignupRequestDTO request);
    ResponseEntity<?> login(LoginRequestDTO request);
    ResponseEntity<?> verify(String email, String token);
}
