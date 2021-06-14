package com.agostinaluciano.cryptopocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodayTransactionsResult {

    private String mostSoldCrypto;
    private BigDecimal mostSoldCryptoAmount;
    private BigDecimal salesAmount;
    private BigDecimal purchaseAmount;


}
