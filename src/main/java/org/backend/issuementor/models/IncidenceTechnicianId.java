package org.backend.issuementor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class IncidenceTechnicianId implements Serializable {
    @Column(name = "incidence_id")
    private long incidenceId;

    @Column(name = "technician_id")
    private long technicianId;
}
