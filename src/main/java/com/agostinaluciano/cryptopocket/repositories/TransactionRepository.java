package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.Transaction;

import java.util.List;


public interface TransactionRepository {

    List<Transaction> getByUser(Integer userId);
}
