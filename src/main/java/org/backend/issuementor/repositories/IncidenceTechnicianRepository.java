package org.backend.issuementor.repositories;

import org.backend.issuementor.models.IncidenceTechnician;
import org.backend.issuementor.models.IncidenceTechnicianId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidenceTechnicianRepository extends JpaRepository<IncidenceTechnician, IncidenceTechnicianId> {
}