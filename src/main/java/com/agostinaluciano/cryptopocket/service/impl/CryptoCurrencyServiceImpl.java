package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.clients.CoinMarketCapClient;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {

    private CoinMarketCapClient coinMarketCapClient;

    private static final String USD_CURRENCY = "USD";

    public CryptoCurrencyServiceImpl(CoinMarketCapClient coinMarketCapClient) {
        this.coinMarketCapClient = coinMarketCapClient;
    }

    @Override
    public List<CurrencyQuoteDTO> getQuotes() {
        log.info("getting quotes from service");
        return coinMarketCapClient.quotes().getData()
                .stream()
                .map(elem -> new CurrencyQuoteDTO(elem.getName(), elem.getQuote().get(USD_CURRENCY).getPrice()))
                .collect(Collectors.toList());
    }
}
