package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CurrencyQuoteDTO {

    private  String crypto;
    private BigDecimal quoteInUsd;


}
