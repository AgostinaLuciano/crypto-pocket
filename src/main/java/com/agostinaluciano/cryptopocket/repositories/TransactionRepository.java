package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.Transaction;

import java.math.BigDecimal;
import java.util.List;


public interface TransactionRepository {

   List<Transaction> getByUser(Integer userId);

    void saveTransaction(Integer userId, int cryptoId, BigDecimal amount, OperationType operationType);
}
