package com.victorfelipejr.stocks_tracker.entities;
import jakarta.persistence.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String stockName;
    private String stockSymbol;
    private Double currentPrice;
    private Double marketHigh;
    private Double marketLow;
    private String lastUpdated;
    private Double previousClose;


    // Constructor
    public Stock() {
    }
    // Constructor with parameters
    public Stock(String name, String symbol, Double currentPrice, Double marketHigh, Double marketLow, Double previousClose, String lastUpdated) {
        this.stockName = name;
        this.stockSymbol = symbol;
        this.currentPrice = currentPrice;
        this.marketHigh = marketHigh;
        this.marketLow = marketLow;
        this.previousClose = previousClose;
        this.lastUpdated = lastUpdated;
    }

    // Getters
    public Long getId() {
        return id;
    }
    public String getStockName() {
        return stockName;
    }
    public String getStockSymbol() {
        return stockSymbol;
    }
    public Double getCurrentPrice() { return currentPrice;}
    public Double getMarketHigh() { return marketHigh; }
    public Double getMarketLow() { return marketLow;}
    public String getLastUpdated() { return lastUpdated; }
    public Double getPreviousClose() { return previousClose; }

    // Setters
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public void setCurrentPrice(Double currentPrice) {this.currentPrice = currentPrice;}
    public void setMarketHigh(Double marketHigh) {
        this.marketHigh = marketHigh;
    }
    public void setMarketLow(Double marketLow) {
        this.marketLow = marketLow;
    }
    public void setLastUpdated(String lastUpdated) { this.lastUpdated = lastUpdated; }
    public void setPreviousClose(Double previousClose) { this.previousClose = previousClose; }
}