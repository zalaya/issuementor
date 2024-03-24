package org.backend.jauth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSafeUserDTO {
    private long id;
    private String username;
    private String email;
}
