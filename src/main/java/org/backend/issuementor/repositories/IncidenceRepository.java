package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Incidence;
import org.backend.issuementor.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenceRepository extends JpaRepository<Incidence, Long> {
    List<Incidence> findByUser(User user);
    List<Incidence> findByTechnician(User user);
}
