package org.backend.issuementor.services;

import org.backend.issuementor.dtos.CredentialsDTO;
import org.backend.issuementor.dtos.UnsafeUserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signup(UnsafeUserDTO request);
    ResponseEntity<?> login(CredentialsDTO request);
    ResponseEntity<?> verify(String email, String token);
}
