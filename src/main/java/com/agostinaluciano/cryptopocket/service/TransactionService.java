package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactionsByUser(Integer id);

}
