package com.agostinaluciano.cryptopocket.clients.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingQuotesResponseDTO {
    private List<ListingElementDTO> data;
}