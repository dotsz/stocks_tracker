package com.victorfelipejr.stocks_tracker.services;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void saveOrUpdateStock(Stock stock){
        stockRepository.save(stock);
    }
    public void deleteStockbyId(Long id){
        stockRepository.deleteById(id);
    }
    public void saveAllStocks(List<Stock> stocks){
        stockRepository.saveAll(stocks);
    }

    @Transactional
    public void resetDatabase(){
        stockRepository.deleteAll();
        stockRepository.flush();
    }




}
