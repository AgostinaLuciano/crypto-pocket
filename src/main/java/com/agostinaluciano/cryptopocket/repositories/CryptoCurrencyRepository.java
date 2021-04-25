package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CryptoCurrencyRepository {
    List<CryptoCurrency> getAll();
}
