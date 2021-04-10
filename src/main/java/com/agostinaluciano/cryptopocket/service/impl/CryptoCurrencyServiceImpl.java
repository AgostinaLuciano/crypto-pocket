package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.clients.CoinMarketCapClient;
import com.agostinaluciano.cryptopocket.clients.responses.ListingQuotesResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService{

    @Autowired
    CoinMarketCapClient coinMarketCapClient;

    @Override
    public ListingQuotesResponseDTO getQuotes() {
      log.info("getting quotes from service");
        return coinMarketCapClient.quotes();
    }
}
