package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "incidence", schema = "issuementor")
public class Incidence {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Lob
    @Column(name = "priority")
    private String priority;

    @Lob
    @Column(name = "status")
    private String status;

}