package com.victorfelipejr.stocks_tracker.services;


import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getStockById(Long stockId) {
        return stockRepository.findById(stockId).orElse(null);
    }

    public Stock saveOrUpdateStock(Stock stock) {
        return stockRepository.save(stock);
    }
    public void deleteStock(Long stockId) {
        stockRepository.deleteById(stockId);
    }

    public Stock updateStockDetails(Long stockId, Double price, Double volume, Double change, Double changePercent, Long timestamp) {
        Stock stock = getStockById(stockId);
        if (stock != null) {
            stock.setStockPrice(price);
            stock.setStockVolume(volume);
            stock.setStockChange(change);
            stock.setStockChangePercent(changePercent);
            stock.setTimestamp(timestamp);
            return stockRepository.save(stock);
        }
        return null;
    }

}
