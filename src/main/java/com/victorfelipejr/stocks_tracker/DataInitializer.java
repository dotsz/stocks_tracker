package com.victorfelipejr.stocks_tracker;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.RapidAPIService;
import com.victorfelipejr.stocks_tracker.services.StockService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final StockService stockService;

    private final RapidAPIService rapidAPIService;


    public DataInitializer(StockService stockService, RapidAPIService rapidAPIService) {
        this.stockService = stockService;
        this.rapidAPIService = rapidAPIService;

    }

    @Override
    public void run(String... args) throws Exception {

        if(stockService.getAllStocks().isEmpty()) {
            List<String> stockTickers = List.of("AAPL", "GOOGL", "MSFT", "AMZN", "TSLA");

            for (String stock_ticker : stockTickers) {
                Stock stock = rapidAPIService.fetchStockInfo(stock_ticker);
                stockService.saveOrUpdateStock(stock);
            }
        }

    }

}





