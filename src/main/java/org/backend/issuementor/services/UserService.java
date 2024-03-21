package org.backend.issuementor.services;

import org.backend.issuementor.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    ResponseEntity<?> findAll(String identification, String token);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String identification);
    boolean existsByEmail(String email);
    User saveEncoded(User user);
    User saveUnencoded(User user);
}
