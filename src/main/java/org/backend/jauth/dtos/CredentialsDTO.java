package org.backend.jauth.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsDTO {
    private String identification;
    private String password;
}
