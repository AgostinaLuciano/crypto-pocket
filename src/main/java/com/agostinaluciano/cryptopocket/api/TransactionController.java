package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.dto.TransactionDTO;
import com.agostinaluciano.cryptopocket.dto.TransferenceDTO;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public List<TransactionDTO> getTransactionsByUser(@PathVariable("id") Integer id) {
        return transactionService.getTransactionsByUser(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void transfer(@RequestBody TransferenceDTO transferenceDTO) {
        log.info("recieving transference order");
        transactionService.transfer(transferenceDTO);
    }
}
