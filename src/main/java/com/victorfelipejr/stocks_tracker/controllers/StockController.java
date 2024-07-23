package com.victorfelipejr.stocks_tracker.controllers;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{stockId}")
    public Stock getStockById(@PathVariable Long stockId) {
        return stockService.getStockById(stockId);
    }

    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.saveOrUpdateStock(stock);
    }


}
