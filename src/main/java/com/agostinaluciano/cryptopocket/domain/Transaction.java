package com.agostinaluciano.cryptopocket.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {

    private Long id;
    private Integer userId;
    private Integer currencyId;
    private BigDecimal amount;
    private OperationType operationType;
    private String transactionDate;
}
