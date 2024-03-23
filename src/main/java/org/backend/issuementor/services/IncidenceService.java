package org.backend.issuementor.services;

import org.backend.issuementor.models.Incidence;
import org.backend.issuementor.models.User;

import java.util.List;
import java.util.Optional;

public interface IncidenceService {
    Optional<Incidence> findById(long id);
    List<Incidence> findByUser(User user);
}
