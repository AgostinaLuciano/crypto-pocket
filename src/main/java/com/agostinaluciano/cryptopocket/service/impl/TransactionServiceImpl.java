package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.coventers.AmountConverter;
import com.agostinaluciano.cryptopocket.coventers.TransactionConverter;
import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.TodayTransactionsResult;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.CurrencyTotalDTO;
import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.dto.TransferenceDTO;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import com.agostinaluciano.cryptopocket.service.PortfolioService;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private CryptoCurrencyService cryptoCurrencyService;
    private UserService userService;
    private PortfolioService portfolioService;


    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  CryptoCurrencyService cryptoCurrencyService,
                                  UserService userService, PortfolioService portfolioService
    ) {
        this.transactionRepository = transactionRepository;
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @Override
    public List<TransactionDTO> getTransactionsByUser(Integer id) throws UserNotFoundException {
        log.info("getting transactions for user {}", id);
        userService.validateUser(id);
        return transactionRepository.getByUser(id)
                .stream()
                .map(transaction -> TransactionConverter.toDTO(transaction,
                        cryptoCurrencyService.getCryptoById(transaction.getCurrencyId()).getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void transfer(TransferenceDTO transferenceDTO) {
        log.info("preparing transference");

        userService.validateUser(transferenceDTO.getReceiverId());
        userService.validateUser(transferenceDTO.getTransmiterId());

        portfolioService.validateFunds(transferenceDTO);

        int cryptoId = cryptoCurrencyService.getCryptoByName(transferenceDTO.getCurrency()).getId();

        transactionRepository.saveTransaction(transferenceDTO.getTransmiterId(), cryptoId, transferenceDTO.getAmount(), OperationType.SELL);
        transactionRepository.saveTransaction(transferenceDTO.getReceiverId(), cryptoId, transferenceDTO.getAmount(), OperationType.BUY);
    }


    @Override
    public TodayTransactionsResult getBuyAndSellStatistics() {

        Map<OperationType, List<Transaction>> SalesAndPurchasesMap = transactionRepository.getTodayTransactions()
                .stream()
                .collect(Collectors.groupingBy(Transaction::getOperationType));

        List<Transaction> sales = SalesAndPurchasesMap.get(OperationType.SELL).stream().collect(Collectors.toList());
        List<Transaction> purchases = SalesAndPurchasesMap.get(OperationType.BUY).stream().collect(Collectors.toList());

        List<CurrencyTotalDTO> salesInUSD = AmountConverter.toUSD(sales, cryptoCurrencyService.getQuotes(), cryptoCurrencyService.getAll());

        List<CurrencyTotalDTO> purchasesInUSD = AmountConverter.toUSD(purchases, cryptoCurrencyService.getQuotes(), cryptoCurrencyService.getAll()).stream().sorted().collect(Collectors.toList());

        BigDecimal totalPurchasesUSD = purchasesInUSD.stream().map(purchase -> purchase.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalSalesUSD = salesInUSD.stream().map(purchase -> purchase.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TodayTransactionsResult(purchasesInUSD.get(purchasesInUSD.size() - 1).getCurrency(), purchasesInUSD.get(purchasesInUSD.size() - 1).getAmount(), totalSalesUSD, totalPurchasesUSD);
    }


}
