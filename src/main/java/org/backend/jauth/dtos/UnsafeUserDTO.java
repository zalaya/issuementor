package org.backend.jauth.dtos;

import lombok.Getter;
import lombok.Setter;
import org.backend.jauth.enumerators.Gender;

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
