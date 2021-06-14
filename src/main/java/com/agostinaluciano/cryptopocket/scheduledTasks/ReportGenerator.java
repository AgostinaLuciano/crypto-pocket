package com.agostinaluciano.cryptopocket.scheduledTasks;

import com.agostinaluciano.cryptopocket.domain.TodayTransactionsResult;
import com.agostinaluciano.cryptopocket.service.PortfolioService;
import com.agostinaluciano.cryptopocket.service.TransactionService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Data
@Component
public class ReportGenerator {

    private TransactionService transactionService;
    private PortfolioService portfolioService;


    public ReportGenerator(TransactionService transactionService, PortfolioService portfolioService) {
        this.transactionService = transactionService;
        this.portfolioService = portfolioService;
    }

    @Scheduled(cron ="0 0 3 * * *")
    public void generateFile() throws IOException {
        log.info("generating file");
        Date today = new Date();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("Transactions report: "+today.toString()));
        TodayTransactionsResult todayTransactionsResult = transactionService.getBuyAndSellStatistics();
        bufferedWriter.write("Most Sold Currency: " + todayTransactionsResult.getMostSoldCrypto() + "\n" +
                "With purchases for (USD): " + todayTransactionsResult.getMostSoldCryptoAmount() + "\n" +
                "Total sales amount: " + todayTransactionsResult.getSalesAmount() + "\n" +
                "Total purchases amount: " + todayTransactionsResult.getPurchaseAmount());
        bufferedWriter.close();
    }
}
