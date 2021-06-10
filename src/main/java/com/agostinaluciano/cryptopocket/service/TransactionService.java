package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.dto.TransferenceDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactionsByUser(Integer id);

    void transfer(TransferenceDTO transferenceDTO);
}
