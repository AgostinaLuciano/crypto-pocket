package com.agostinaluciano.cryptopocket.api.exception;

import com.agostinaluciano.cryptopocket.exception.CoinMarketCapClientExeption;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleInternalServerError(RuntimeException runtimeException) {
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleError(UserNotFoundException userNotFoundException) {
    ApiErrorMessage apiErrorMessage = new ApiErrorMessage("User Not Found");
    return ResponseEntity.status(404).body(apiErrorMessage);
    }


    @ExceptionHandler(value ={DataAccessException.class})
    public ResponseEntity<?> handleTransactionError(DataAccessException dataAccessException){
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(value = {CoinMarketCapClientExeption.class})
    public ResponseEntity<?> handleCoinMarketClientExeption(){
        ApiErrorMessage apiErrorMessage =  new ApiErrorMessage("failed trying to connect to CoinMarketCapApi");
        return ResponseEntity.status(500).body(apiErrorMessage);
    }
}
