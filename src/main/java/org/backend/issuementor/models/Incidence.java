package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "incidences", schema = "issuementor")
public class Incidence {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "date")
    private Instant date;

    @Lob
    @Column(name = "priority")
    private String priority;

    @Lob
    @Column(name = "status")
    private String status;

}