package com.agostinaluciano.cryptopocket.clients;

import com.agostinaluciano.cryptopocket.exception.CoinMarketCapClientExeption;
import com.agostinaluciano.cryptopocket.clients.responses.ListingQuotesResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class CoinMarketCapClient {
    private RestTemplate restTemplate;
    private String apiKey;
    private String url;

    final private String API_KEY_HEADER = "X-CMC_PRO_API_KEY";

    public CoinMarketCapClient(RestTemplate restTemplate, @Value("${coinmarketcap.api-key}") String apiKey,
                               @Value("${coinmarketcap.url}") String url) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.url = url;
    }

    public ListingQuotesResponseDTO quotes() {
        log.info("getting quotes from coinmarketcap");
        ResponseEntity<ListingQuotesResponseDTO> respones =
                restTemplate.exchange(url, HttpMethod.GET, createRequestEntityWithHeader(API_KEY_HEADER, apiKey), ListingQuotesResponseDTO.class);
        //exchage permite pasar header (vs getForENtity que no)
        return handleResponse(respones);
    }

    private HttpEntity<String> createRequestEntityWithHeader(String header, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, value);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

    private ListingQuotesResponseDTO handleResponse(ResponseEntity<ListingQuotesResponseDTO> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.info("coinmarket cap responded with {} status code", response.getStatusCode());
            throw new CoinMarketCapClientExeption();
        }
        return response.getBody();

    }
}
