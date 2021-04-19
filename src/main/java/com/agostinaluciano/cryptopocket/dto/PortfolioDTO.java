package com.agostinaluciano.cryptopocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PortfolioDTO {

    private Integer userId;
    private List<CurrencyTotalDTO> currencies;
    private BigDecimal totalInUsd;
    private LocalDateTime date;

}
