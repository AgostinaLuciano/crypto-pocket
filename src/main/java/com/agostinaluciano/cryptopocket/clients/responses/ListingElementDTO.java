package com.agostinaluciano.cryptopocket.clients.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingElementDTO {
    private String name;
    private String symbol;

    private Map<String, QuoteDTO> quote;
}