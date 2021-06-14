package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
public class TransactionRepositoryImpl implements TransactionRepository {


    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Transaction> rowMapper = ((rs, rownum) -> new Transaction(rs.getLong("id"),
            rs.getInt("user_Id"), rs.getInt("crypto_currency_id"), rs.getBigDecimal("amount"),
            OperationType.valueOf(rs.getString("operation_type")), rs.getString("transaction_date")));

    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override

    public List<Transaction> getByUser(Integer userId) {
        log.info("getting transactions for user id = {}", userId);
        List<Transaction> transactionList = jdbcTemplate.query("SELECT id, user_id, crypto_currency_id, amount, " +
                "operation_type, transaction_date FROM transaction WHERE user_id = ?", rowMapper, userId);
        return transactionList;
    }

    @Override
    public void saveTransaction(Integer userId, int cryptoId, BigDecimal amount, OperationType operationType) {

        log.info("verifying amount and inserting in DB");
        if (operationType == OperationType.SELL) {
            amount = amount.negate();
        }

        jdbcTemplate.update("INSERT into \"transaction\" (user_id, crypto_currency_id, amount, operation_type, transaction_date) VALUES (?, ?, ?, ?, ?)",
                userId, cryptoId, amount, operationType.name(), LocalDateTime.now());

    }

    @Override
    public List<Transaction> getTodayTransactions() {
        log.info("getting transferences in last 24 hs");
        List<Transaction> last24hsTransactions = jdbcTemplate.query("SELECT id, user_id, crypto_currency_id, amount, operation_type, transaction_date FROM \"transaction\" WHERE transaction_date between current_date - INTEGER '1' and current_date", rowMapper);
        return last24hsTransactions;
    }


}

