package org.backend.issuementor.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class IncidenceUserId implements Serializable {
    @Column(name = "incidence_id")
    private long incidenceId;

    @Column(name = "user_id")
    private long userId;
}
