package com.victorfelipejr.stocks_tracker.services;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Stock getStockByID(Long id){
        return stockRepository.findById(id).orElse(null);
    }
    public Stock saveOrUpdateStock(Stock stock){
        return stockRepository.save(stock);
    }
    public void deleteStock(Long id){
        stockRepository.deleteById(id);
    }

    public void saveAllStocks(List<Stock> stocks){
        stockRepository.saveAll(stocks);
    }

}
