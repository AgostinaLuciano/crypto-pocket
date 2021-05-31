package com.agostinaluciano.cryptopocket.service.impl;

import com.agostinaluciano.cryptopocket.coventers.TransactionConverter;
import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.exception.UserNotFoundException;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import com.agostinaluciano.cryptopocket.service.CryptoCurrencyService;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import com.agostinaluciano.cryptopocket.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {


    @Mock
    private UserService userService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private CryptoCurrencyService cryptoCurrencyService;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository, cryptoCurrencyService, userService);
    }

    @Test
    void whenUserIsInvalidShouThrowUserNotFoundException() {

        int userId = 100;
        doThrow(UserNotFoundException.class).when(userService).validateUser(userId);
        assertThrows(UserNotFoundException.class, () -> transactionService.getTransactionsByUser(userId));

    }

    @Test
    void WhenUserHasNoTransactionShouldReturnEmptyList() {
        int userId = 10;

        when(transactionRepository.getByUser(userId)).thenReturn(EMPTY_LIST);

        List<TransactionDTO> result = transactionService.getTransactionsByUser(userId);


        assertThat(result).isEqualTo(EMPTY_LIST);
    }

    @Test
    void WhenUserHasOneTransactionShouldReturnTransaction() {

        int userId = 12;
        Long transactionId = Long.valueOf(8);
        int currencyId = 1;

        CryptoCurrency cryptoCurrency = new CryptoCurrency(currencyId, "bitcoin", "BIT");
        Transaction t = new Transaction(transactionId, userId, currencyId, BigDecimal.valueOf(100000), OperationType.BUY, "14/05/2021");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(t);

        when(cryptoCurrencyService.getCryptoById(currencyId)).thenReturn(cryptoCurrency);
        when(transactionRepository.getByUser(userId)).thenReturn(transactions);

        List<TransactionDTO> result = transactionService.getTransactionsByUser(userId);
        List<TransactionDTO> expectedResult = new ArrayList<>();
        expectedResult.add(new TransactionDTO(transactionId, cryptoCurrency.getName(), BigDecimal.valueOf(100000), OperationType.BUY, "14/05/2021"));


        assertEquals(false, result.isEmpty());
        assertEquals(result.size(), 1);
        assertEquals(result, expectedResult);
    }

    @Test
    public void whenUserHasTwoTransactionsShouldReturnAComplexList() {
        int userId = 12;
        Long transactionId = Long.valueOf(8);
        int currencyId = 1;

        CryptoCurrency cryptoCurrency = new CryptoCurrency(currencyId, "bitcoin", "BIT");

        Transaction firstTransaction = new Transaction(transactionId, userId, currencyId, BigDecimal.valueOf(100000), OperationType.BUY, "14/05/2021");
        Transaction secondTransaction = new Transaction(transactionId + 1, userId, currencyId, BigDecimal.valueOf(5), OperationType.SELL, "19/08/2020");

        List<Transaction> transactions = new ArrayList<>();

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);

        when(cryptoCurrencyService.getCryptoById(currencyId)).thenReturn(cryptoCurrency);
        when(transactionRepository.getByUser(userId)).thenReturn(transactions);

        List<TransactionDTO> result = transactionService.getTransactionsByUser(userId);
        List<TransactionDTO> expectedResult = new ArrayList<>();

        TransactionDTO secondTransactionDTO = TransactionConverter.toDTO(secondTransaction, "bitcoin");

        expectedResult.add(TransactionConverter.toDTO(firstTransaction, "bitcoin"));
        expectedResult.add(TransactionConverter.toDTO(secondTransaction, "bitcoin"));

        assertEquals(false, result.isEmpty());
        assertEquals(result.size(), 2);
        assertEquals(result, expectedResult);
        assertEquals(result.get(1), secondTransactionDTO);
    }


}