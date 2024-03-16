package org.backend.issuementor.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicUserDTO {
    private long id;
    private String username;
    private String email;
}