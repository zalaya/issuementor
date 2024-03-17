package org.backend.issuementor.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.backend.issuementor.enumerators.Gender;
import org.backend.issuementor.enumerators.Role;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "login_date")
    private Timestamp loginDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
