package org.backend.issuementor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class IncidenceTechnicianId implements Serializable {
    private static final long serialVersionUID = -2895696602053269201L;
    @Column(name = "incidence_id", nullable = false)
    private Long incidenceId;

    @Column(name = "technician_id", nullable = false)
    private Long technicianId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IncidenceTechnicianId entity = (IncidenceTechnicianId) o;
        return Objects.equals(this.incidenceId, entity.incidenceId) &&
            Objects.equals(this.technicianId, entity.technicianId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidenceId, technicianId);
    }

}