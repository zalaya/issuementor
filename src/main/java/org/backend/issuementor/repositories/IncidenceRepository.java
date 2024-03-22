package org.backend.issuementor.repositories;

import org.backend.issuementor.models.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenceRepository extends JpaRepository<Incidence, Long> {

}
