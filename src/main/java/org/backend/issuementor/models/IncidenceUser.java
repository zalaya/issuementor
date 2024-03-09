package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "incidence_users")
@Getter
@Setter
public class IncidenceUser {
    @EmbeddedId
    private IncidenceUserId id;

    @ManyToOne
    @MapsId("incidenceId")
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;
}