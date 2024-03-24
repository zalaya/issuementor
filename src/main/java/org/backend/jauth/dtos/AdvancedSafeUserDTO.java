package org.backend.jauth.dtos;

import lombok.Getter;
import lombok.Setter;
import org.backend.jauth.enumerators.Gender;
import org.backend.jauth.enumerators.Role;

@Getter
@Setter
public class AdvancedSafeUserDTO {
    private String name;
    private String surname;
    private String username;
    private String email;
    private Role role;
    private Gender gender;
}
