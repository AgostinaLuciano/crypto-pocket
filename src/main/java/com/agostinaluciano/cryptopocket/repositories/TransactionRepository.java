package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.Transaction;

import java.util.List;
import java.util.Optional;


public interface TransactionRepository {

    Optional<List<Transaction>> getByUser(Integer userId);
}
