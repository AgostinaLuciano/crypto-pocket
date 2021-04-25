package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;
import com.agostinaluciano.cryptopocket.dto.CurrencyTotalDTO;
import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;
import com.agostinaluciano.cryptopocket.repositories.CryptoCurrencyRepository;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import com.agostinaluciano.cryptopocket.service.PortfolioService;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class PortfolioServiceImpl implements PortfolioService {


    private UserService userService;
    private CryptoCurrencyService cryptoCurrencyService;
    private TransactionRepository transactionRepository;
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    public PortfolioServiceImpl(UserService userService, CryptoCurrencyService cryptoCurrencyService, TransactionRepository transactionRepository, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.userService = userService;
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.transactionRepository = transactionRepository;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    @Override
    public PortfolioDTO getPortfolio(Integer userId) {
        log.info("getting portfolio for {} from service", userId);
        userService.validateUser(userId);
        List<CurrencyQuoteDTO> currencyQuoteDTOList = cryptoCurrencyService.getQuotes();
        List<Transaction> transactionList = transactionRepository.getByUser(userId);
        return buildPortfolio(currencyQuoteDTOList, transactionList, userId);
    }

    private PortfolioDTO buildPortfolio(List<CurrencyQuoteDTO> currencyQuoteDTOList,
                                        List<Transaction> transactionList, Integer userId) {

        //mapa obtenido de coinmarketcap con cotizaciones actuales
        Map<String, BigDecimal> quoteInUsd = currencyQuoteDTOList.stream()
                .collect(Collectors.toMap(crypto -> crypto.getCrypto(), crypto -> crypto.getQuoteInUsd()));

        List<CryptoCurrency> cryptoCurrencies = cryptoCurrencyRepository.getAll();

        //mapa del id y nombre de la criptomoneda
        Map<Integer, String> cryptoMap = cryptoCurrencies.stream()
                .collect(Collectors.toMap(crypto -> crypto.getId(), crypto -> crypto.getName()));

        //total de c/ crypto (US$)
        List<CurrencyTotalDTO> currencyTotalDTOList = transactionList.stream()
                .collect(groupingBy(Transaction::getCurrencyId)) //Map<Integer, List<Transaction>>
                .entrySet()//Set<Integer, List<Transaction>>
                .stream()
                .map(entry -> new CurrencyTotalDTO(cryptoMap.get(entry.getKey()), entry.getValue()
                        .stream()
                        .map(transaction -> transaction.getAmount())
                        .reduce(BigDecimal.valueOf(0), (monto, montoOtro) -> (monto.add(montoOtro)))))
                .collect(Collectors.toList());

        BigDecimal totalUsd= currencyTotalDTOList.stream()
                .map(currency -> currency.getAmount().multiply((quoteInUsd.get(currency.getCurrency()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        LocalDateTime localDateTime = LocalDateTime.now();
        PortfolioDTO portfolioDTO = new PortfolioDTO(userId, currencyTotalDTOList, totalUsd, localDateTime);
        return portfolioDTO;
    }
}
