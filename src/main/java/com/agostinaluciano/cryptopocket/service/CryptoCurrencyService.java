package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.clients.responses.ListingQuotesResponseDTO;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;

import java.util.List;

public interface CryptoCurrencyService {


    List<CurrencyQuoteDTO> getQuotes();


}
