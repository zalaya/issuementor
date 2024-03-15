package org.backend.issuementor.mappers;

import org.backend.issuementor.dtos.UserDTO;
import org.backend.issuementor.models.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());

        return userDTO;
    }
}
