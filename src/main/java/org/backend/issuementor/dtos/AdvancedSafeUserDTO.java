package org.backend.issuementor.dtos;

import lombok.Getter;
import lombok.Setter;
import org.backend.issuementor.enumerators.Gender;
import org.backend.issuementor.enumerators.Role;

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
