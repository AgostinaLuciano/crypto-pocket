package com.agostinaluciano.cryptopocket.api.exception;

import com.agostinaluciano.cryptopocket.exception.CryptoCurrencyExeption;
import com.agostinaluciano.cryptopocket.exception.TransactionNotFoundExeption;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
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

    //TODO otro hadler
    @ExceptionHandler(value ={TransactionNotFoundExeption.class})
    public ResponseEntity<?> handleTransactionError(TransactionNotFoundExeption transactionNotFoundExeption){
        ApiErrorMessage apiErrorMessage= new ApiErrorMessage("error getting transactions");
        return ResponseEntity.status(404).body(apiErrorMessage);
    }
    @ExceptionHandler(value = {CryptoCurrencyExeption.class})
    public ResponseEntity<?> handleCryptoCurrencyError(CryptoCurrencyExeption cryptoCurrencyExeption){
        ApiErrorMessage apiErrorMessage = new ApiErrorMessage("information cannot be retrieved from CryptoCurrency");
        return ResponseEntity.unprocessableEntity().body(apiErrorMessage);
    }
    @ExceptionHandler(value = {CoinMarketCapClientExeption.class})
    public ResponseEntity<?> handleCoinMarketClientExeption(){
        ApiErrorMessage apiErrorMessage =  new ApiErrorMessage("failed trying to connect to CoinMarketCapApi");
        return ResponseEntity.status(504).body(apiErrorMessage);
    }
}
