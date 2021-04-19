package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;
import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import com.agostinaluciano.cryptopocket.service.PortfolioService;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PortfolioServiceImpl implements PortfolioService {


    private UserService userService;
    private CryptoCurrencyService cryptoCurrencyService;
    private TransactionRepository transactionRepository;

    public PortfolioServiceImpl(UserService userService, CryptoCurrencyService cryptoCurrencyService, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public PortfolioDTO getPortfolio(Integer userId) {
        log.info("getting portfolio for {} from service", userId);
        userService.validateUser(userId);
        List<CurrencyQuoteDTO> currencyQuoteDTOList=cryptoCurrencyService.getQuotes();
        List<Transaction> transactionList= transactionRepository.getByUser(userId);
        return buildPortfolio(currencyQuoteDTOList, transactionList);
    }

    private PortfolioDTO buildPortfolio(List<CurrencyQuoteDTO> currencyQuoteDTOList,
                                        List<Transaction> transactionList){
        Map<String, BigDecimal> quoteInUsd= currencyQuoteDTOList.stream()
                .collect(Collectors.toMap(crypto-> crypto.getCrypto(), crypto->crypto.getQuoteInUsd()));
        return null;
    }
}
