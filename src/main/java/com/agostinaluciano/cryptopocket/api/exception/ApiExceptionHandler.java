package com.agostinaluciano.cryptopocket.api.exception;

import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    ApiErrorMessage apiErrorMessage;

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleInternalServerError(RuntimeException runtimeException) {
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleError(UserNotFoundException userNotFoundException) {
        apiErrorMessage = new ApiErrorMessage("User Not Found");
        return ResponseEntity.status(404).body(apiErrorMessage.getErrorMessage());
    }

}
