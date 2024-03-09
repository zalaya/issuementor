package org.backend.issuementor.repositories;

import org.backend.issuementor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}