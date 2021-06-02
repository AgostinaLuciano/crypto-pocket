package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;

import java.util.List;

public interface CryptoCurrencyService {


    List<CurrencyQuoteDTO> getQuotes();

    CryptoCurrency getCryptoById(Integer id);


}
