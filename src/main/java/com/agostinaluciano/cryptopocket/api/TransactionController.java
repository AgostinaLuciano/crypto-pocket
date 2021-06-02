package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionSevice;

    public TransactionController(TransactionService transactionSevice) {
        this.transactionSevice = transactionSevice;
    }

    @GetMapping("/{id}")
    public List<TransactionDTO> getTransactionsByUser(@PathVariable("id") Integer id) {
        return transactionSevice.getTransactionsByUser(id);
    }
}
