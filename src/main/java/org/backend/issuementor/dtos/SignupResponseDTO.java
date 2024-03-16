package org.backend.issuementor.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignupResponseDTO {
    private long id;
    private String username;
    private String email;
}
