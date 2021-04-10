package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.clients.responses.ListingQuotesResponseDTO;

public interface CryptoCurrencyService {

    ListingQuotesResponseDTO getQuotes();
}
