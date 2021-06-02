package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.coventers.TransactionConverter;
import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.repositories.CryptoCurrencyRepository;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import com.agostinaluciano.cryptopocket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
   private CryptoCurrencyService cryptoCurrencyService;
    private UserService userService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CryptoCurrencyService cryptoCurrencyService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.userService = userService;
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
}
