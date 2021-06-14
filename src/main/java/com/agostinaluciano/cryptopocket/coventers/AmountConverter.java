package com.agostinaluciano.cryptopocket.coventers;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.CurrencyQuoteDTO;
import com.agostinaluciano.cryptopocket.dto.CurrencyTotalDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class AmountConverter {

    public static List<CurrencyTotalDTO> toUSD(List<Transaction> transactionList,
                                               List<CurrencyQuoteDTO> currencyQuoteDTOList,
                                               List<CryptoCurrency> cryptoCurrenciesIdentifier){

        Map<String, BigDecimal> quoteInUsd = currencyQuoteDTOList.stream()
                .collect(Collectors.toMap(crypto -> crypto.getCrypto(), crypto -> crypto.getQuoteInUsd()));

        Map<Integer, String> cryptoMap = cryptoCurrenciesIdentifier.stream()
                .collect(Collectors.toMap(crypto -> crypto.getId(), crypto -> crypto.getName()));

        List<CurrencyTotalDTO> currencyTotalDTOList = transactionList.stream()
                .collect(groupingBy(Transaction::getCurrencyId)) //Map<Integer, List<Transaction>>
                .entrySet()//Set<Integer, List<Transaction>>
                .stream()
                .map(entry -> new CurrencyTotalDTO(cryptoMap.get(entry.getKey()), entry.getValue()
                        .stream()
                        .filter(transaction -> quoteInUsd.containsKey(cryptoMap.get(transaction.getCurrencyId())))
                        .map(transaction -> transaction.getAmount())
                        .reduce(BigDecimal.valueOf(0), (amount, otherAmount) -> amount.add(otherAmount)))).collect(Collectors.toList());

        List<CurrencyTotalDTO> currencyTotalDTOListUSD =  currencyTotalDTOList.stream()
                .map(currency -> new CurrencyTotalDTO( currency.getCurrency(),
                        currency.getAmount().multiply(quoteInUsd.get(currency.getCurrency())))).collect(Collectors.toList());


        return currencyTotalDTOListUSD;
    }
}
