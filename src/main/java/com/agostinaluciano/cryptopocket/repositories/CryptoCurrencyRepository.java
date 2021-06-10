package com.agostinaluciano.cryptopocket.repositories;

import com.agostinaluciano.cryptopocket.domain.CryptoCurrency;

import java.util.List;
import java.util.Optional;


public interface CryptoCurrencyRepository {
    List<CryptoCurrency> getAll();

    CryptoCurrency getCryptoById(Integer id);

    CryptoCurrency getCryptoByName(String name);

}
