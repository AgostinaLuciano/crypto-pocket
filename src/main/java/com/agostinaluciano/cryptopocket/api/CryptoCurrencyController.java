package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.clients.responses.ListingQuotesResponseDTO;
import com.agostinaluciano.cryptopocket.service.impl.CryptoCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
@Slf4j
public class CryptoCurrencyController {

    @Autowired
    CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/quotes")
    public ListingQuotesResponseDTO getQuotes() {
        log.info("Getting quotes from marketcap");
        return cryptoCurrencyService.getQuotes();
    }
}
