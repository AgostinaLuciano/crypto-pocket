package com.agostinaluciano.cryptopocket.api;

import com.agostinaluciano.cryptopocket.dto.PortfolioDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/portfolios")
public class PortfolioController {

    @RequestMapping("/{userId}")
    public PortfolioDTO getPortfolio(@PathVariable("userId") Integer userId){
        log.info("gettin portfolio for userId: {}",userId);
        return null;
    }
}
