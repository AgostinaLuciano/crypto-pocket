package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String email;

}
