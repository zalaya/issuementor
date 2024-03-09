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
public class IncidenceUserId implements Serializable {
    private static final long serialVersionUID = 8208846400913991369L;
    @Column(name = "incidence_id", nullable = false)
    private Long incidenceId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IncidenceUserId entity = (IncidenceUserId) o;
        return Objects.equals(this.incidenceId, entity.incidenceId) &&
            Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidenceId, userId);
    }

}