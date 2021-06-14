package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.TodayTransactionsResult;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.CurrencyTotalDTO;
import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.dto.TransferenceDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransactionsByUser(Integer id);

    void transfer(TransferenceDTO transferenceDTO);

    TodayTransactionsResult getBuyAndSellStatistics();

}

