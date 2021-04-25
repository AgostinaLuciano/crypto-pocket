package com.agostinaluciano.cryptopocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class CryptoCurrency {
    private int id;
    private String name;
    private String symbol;

}
