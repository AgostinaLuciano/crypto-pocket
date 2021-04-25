package com.agostinaluciano.cryptopocket.repositories.impl;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import com.agostinaluciano.cryptopocket.repositories.CryptoCurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class CryptoCurrencyRepositoryImpl implements CryptoCurrencyRepository {

    private JdbcTemplate jdbcTemplate;
    private final RowMapper<CryptoCurrency> cryptoCurrencyRowMapper =
            ((resultSet, i) -> (new CryptoCurrency(resultSet.getInt("id"),
                    resultSet.getString("name"), resultSet.getString("Symbol"))));

    public CryptoCurrencyRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<CryptoCurrency> getAll() {
        log.info("getting all cryptocurrencies from repository"); //TODO
        List<CryptoCurrency> cryptoList = jdbcTemplate.query("SELECT * FROM \"cryptocurrency\" ", cryptoCurrencyRowMapper);
        return cryptoList;

    }
}
