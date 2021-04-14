package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor //constructor que pide todos los parametros
@Data //da los getter y setter
public class CurrencyQuoteDTO {

    private  String crypto;
    private BigDecimal quoteInUsd;


}
