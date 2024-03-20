package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.backend.issuementor.enumerators.Priority;
import org.backend.issuementor.enumerators.Status;

import java.sql.Timestamp;

@Entity
@Table(name = "incidences")
@Getter
@Setter
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title", length = 150, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_date", nullable = false, insertable = false)
    private Timestamp creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false, insertable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, insertable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;
}
