package org.backend.issuementor.repositories;

import org.backend.issuementor.models.IncidenceUser;
import org.backend.issuementor.models.IncidenceUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenceUserRepository extends JpaRepository<IncidenceUser, IncidenceUserId> {
}