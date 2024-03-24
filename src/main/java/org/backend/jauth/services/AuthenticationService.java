package org.backend.jauth.services;

import org.backend.jauth.dtos.CredentialsDTO;
import org.backend.jauth.dtos.UnsafeUserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signup(UnsafeUserDTO request);
    ResponseEntity<?> login(CredentialsDTO request);
    ResponseEntity<?> verify(String email, String token);
}
