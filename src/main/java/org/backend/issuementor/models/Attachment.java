package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attachments", schema = "issuementor")
public class Attachment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "incidence_id", nullable = false)
    private Incidence incidence;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

}