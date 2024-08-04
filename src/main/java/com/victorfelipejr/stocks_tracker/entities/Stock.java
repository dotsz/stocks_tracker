package com.victorfelipejr.stocks_tracker.entities;
import jakarta.persistence.*;

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
    private Double fifthyDayAverage;
    private String currentDate;

    // Constructor
    public Stock() {
    }

    public Stock(String name, String symbol, Double currentPrice, Double marketHigh, Double marketLow, Double average, String currentDate) {
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
    public Double getCurrentPrice() {
        return currentPrice;
    }
    public Double getMarketHigh() {
        return marketHigh;
    }
    public Double getMarketLow() {
        return marketLow;
    }
    public Double getFifthyDayAverage() {
        return fifthyDayAverage;
    }
    public String getCurrentDate() {
        return currentDate;
    }

    // Setters
    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
    public void setMarketHigh(Double marketHigh) {
        this.marketHigh = marketHigh;
    }
    public void setMarketLow(Double marketLow) {
        this.marketLow = marketLow;
    }
    public void setFifthyDayAverage(Double fifthyDayAverage) {
        this.fifthyDayAverage = fifthyDayAverage;
    }
    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

}