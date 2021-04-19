package com.agostinaluciano.cryptopocket.service;

import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;

public interface PortfolioService {

    PortfolioDTO getPortfolio(Integer userId);
}
