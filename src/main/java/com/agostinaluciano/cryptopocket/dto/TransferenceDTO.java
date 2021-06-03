package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class TransferenceDTO {

    private String currency;
    private BigDecimal amount;
    private Integer receiverId;
    private Integer transmiterId;
}
