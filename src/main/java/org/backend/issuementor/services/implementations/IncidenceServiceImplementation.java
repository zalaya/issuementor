package org.backend.issuementor.services.implementations;

import org.backend.issuementor.models.Incidence;
import org.backend.issuementor.repositories.IncidenceRepository;
import org.backend.issuementor.services.IncidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncidenceServiceImplementation implements IncidenceService {
    @Autowired
    private IncidenceRepository incidenceRepository;

    @Override
    public Optional<Incidence> findById(long id) {
        return incidenceRepository.findById(id);
    }
}
