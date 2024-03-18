package org.backend.issuementor.repositories;

import org.backend.issuementor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    @Query("SELECT user FROM User user WHERE user.username = :identification OR user.email = :identification")
    Optional<User> findByUsernameOrEmail(String identification);
    boolean existsByEmail(String email);
}
