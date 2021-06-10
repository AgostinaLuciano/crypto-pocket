package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;
import com.agostinaluciano.cryptopocket.dto.TransferenceDTO;

public interface PortfolioService {

    PortfolioDTO getPortfolio(Integer userId);

    void validateFunds(TransferenceDTO transferenceDTO);
}
