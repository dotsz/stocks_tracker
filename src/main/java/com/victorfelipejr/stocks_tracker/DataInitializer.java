package com.victorfelipejr.stocks_tracker;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.StockApiService;
import com.victorfelipejr.stocks_tracker.services.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final StockService stockService;
    private final StockApiService stockApiService;

    public DataInitializer(StockService stockService, StockApiService stockApiService) {
        this.stockService = stockService;
        this.stockApiService = stockApiService;
    }

    @Override
    public void run(String... args) throws Exception {


        List<Stock> stocks = new ArrayList<>();
        stocks.addAll(stockApiService.fetchStocks("AAPL"));
        stocks.addAll(stockApiService.fetchStocks("GOOGL"));
        stocks.addAll(stockApiService.fetchStocks("MSFT"));
        stocks.addAll(stockApiService.fetchStocks("AMZN"));
        stocks.addAll(stockApiService.fetchStocks("TSLA"));

        stockService.saveAllStocks(stocks);


    }

}
