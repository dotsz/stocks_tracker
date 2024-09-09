package com.victorfelipejr.stocks_tracker.externalservice;

import com.victorfelipejr.stocks_tracker.entities.Stock;

public interface StockDataService {
    Stock getStockData(String stockSymbol);
}
