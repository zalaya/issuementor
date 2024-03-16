package org.backend.issuementor.dtos;

import lombok.Getter;
import lombok.Setter;
import org.backend.issuementor.models.Gender;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
public class SignupRequestDTO {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Date birthDate;
    private Timestamp creationDate;
    private Gender gender;
}
