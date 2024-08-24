package com.victorfelipejr.stocks_tracker.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.victorfelipejr.stocks_tracker.entities.Stock;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    Stock findByStockSymbol(String stockSymbol);
}