package org.backend.issuementor.dtos;

import lombok.Getter;
import lombok.Setter;
import org.backend.issuementor.enumerators.Gender;

@Getter
@Setter
public class UnsafeUserDTO {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Gender gender;
}
