package com.agostinaluciano.cryptopocket.coventers;

import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.dto.TransactionDTO;

public class TransactionConverter {

    public static TransactionDTO toDTO(Transaction transaction, String name) {

        TransactionDTO transactionDTO = new TransactionDTO(transaction.getId(),
                name, transaction.getAmount(), transaction.getOperationType(), transaction.getTransactionDate());
        return transactionDTO;
    }

}
