package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incidence_technician")
@Getter
@Setter
public class IncidenceTechnician {
    @EmbeddedId
    private IncidenceTechnicianId id;

    @ManyToOne
    @MapsId("incidenceId")
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;

    @ManyToOne
    @MapsId("technicianId")
    @JoinColumn(name = "technician_id")
    private User technician;
}
