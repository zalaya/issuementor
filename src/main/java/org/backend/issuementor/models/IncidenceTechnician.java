package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "incidence_technicians", schema = "issuementor")
public class IncidenceTechnician {
    @EmbeddedId
    private IncidenceTechnicianId id;

    @MapsId("incidenceId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incidence_id", nullable = false)
    private Incidence incidence;

    @MapsId("technicianId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technician_id", nullable = false)
    private User technician;

}