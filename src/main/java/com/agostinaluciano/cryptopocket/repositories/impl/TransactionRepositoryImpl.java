package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.OperationType;
import com.agostinaluciano.cryptopocket.domain.Transaction;
import com.agostinaluciano.cryptopocket.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class TransactionRepositoryImpl implements TransactionRepository {


    private JdbcTemplate jdbcTemplate;
    private final RowMapper<Transaction>  rowMapper = ((rs, rownum) -> new Transaction(rs.getLong("id"),
            rs.getInt("user_Id"), rs.getInt("crypto_currency_id"), rs.getBigDecimal("amount"),
            OperationType.valueOf(rs.getString("operation_type")), rs.getString("transaction_date")));

    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> getByUser(Integer userId) {
        log.info("getting transactions for user id = {}", userId);
        List<Transaction> transactionList = jdbcTemplate.query("SELECT * FROM transaction WHERE user_id = ?", rowMapper, userId);
        return transactionList;
    }//TODO
}
