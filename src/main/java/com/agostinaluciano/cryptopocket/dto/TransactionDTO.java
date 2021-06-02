package com.agostinaluciano.cryptopocket.dto;

import com.agostinaluciano.cryptopocket.domain.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private String currency;
    private BigDecimal amount;
    private OperationType operationType;
    private String transactionDate;
}
