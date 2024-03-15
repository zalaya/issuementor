package org.backend.issuementor.mappers;

import org.backend.issuementor.dtos.CredentialsDTO;
import org.backend.issuementor.models.User;

public class CredentialsMapper {
    public static CredentialsDTO toCredentialsDTO(User user) {
        CredentialsDTO credentialsDTO = new CredentialsDTO();

        credentialsDTO.setEmail(user.getEmail());
        credentialsDTO.setPassword(user.getPassword());

        return credentialsDTO;
    }
}
