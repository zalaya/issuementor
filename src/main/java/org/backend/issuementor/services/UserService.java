package org.backend.issuementor.services;

import org.backend.issuementor.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String identification);
    List<User> findAll();
    boolean existsByEmail(String email);
    User saveEncoded(User user);
    User saveUnencoded(User user);
}
