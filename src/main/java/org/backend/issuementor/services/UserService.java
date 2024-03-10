package org.backend.issuementor.services;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
}
