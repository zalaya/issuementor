package org.backend.issuementor.services;

import org.backend.issuementor.models.Incidence;

import java.util.Optional;

public interface IncidenceService {
    Optional<Incidence> findById(long id);
}
