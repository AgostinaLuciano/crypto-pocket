package com.agostinaluciano.cryptopocket.api.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class ApiErrorMessage {

    private String errorMessage;


}
