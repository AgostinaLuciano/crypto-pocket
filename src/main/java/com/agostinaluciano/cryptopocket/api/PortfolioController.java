package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;
import com.agostinaluciano.cryptopocket.service.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @RequestMapping("/{userId}")
    public PortfolioDTO getPortfolio(@PathVariable("userId") Integer userId){
        log.info("getting portfolio for userId: {}",userId);
        return portfolioService.getPortfolio(userId);

    }
}
