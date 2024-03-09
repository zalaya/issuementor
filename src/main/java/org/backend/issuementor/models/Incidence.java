package org.backend.issuementor.models;

import jakarta.persistence.*;
import org.backend.issuementor.enumerators.Priority;
import org.backend.issuementor.enumerators.Status;

import java.sql.Timestamp;

@Entity
@Table(name = "incidences")
public class Incidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "priority")
    private Priority priority;

    @Column(name = "status")
    private Status status;
}
