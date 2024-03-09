package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "change_history", schema = "issuementor")
public class ChangeHistory {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incidence_id", nullable = false)
    private Incidence incidence;

    @Column(name = "field_changed", length = 50)
    private String fieldChanged;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "change_date")
    private Instant changeDate;

}