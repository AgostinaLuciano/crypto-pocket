package com.agostinaluciano.cryptopocket.coventers;

import com.agostinaluciano.cryptopocket.domain.User;
import com.agostinaluciano.cryptopocket.dto.UserDTO;

public class UserConverter {

    public static UserDTO toDto(User user) {

        UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getEmail());
        return userDTO;


    }
}
