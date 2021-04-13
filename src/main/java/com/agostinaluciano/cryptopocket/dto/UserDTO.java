package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String userName;
    private String email;

}
