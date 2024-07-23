package com.victorfelipejr.stocks_tracker.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column(name = "stock_name")
    private String stockName;
    @Column(name = "stock_ticker")
    private String stockTicker;
    @Column(name = "stock_price")
    private Double stockPrice;
    @Column(name = "stock_volume")
    private Double stockVolume;
    @Column(name = "stock_change")
    private Double stockChange;
    @Column(name = "stock_change_percent")
    private Double stockChangePercent;
    @Column(name = "timestamp")
    private Long timestamp;

    // Getters
    public Long getStockId() { return stockId; }
    public String getStockName() { return stockName;}
    public String getStockTicker() { return stockTicker; }
    public Double getStockPrice() { return stockPrice; }
    public Double getStockVolume() { return stockVolume; }
    public Double getStockChange() { return stockChange; }
    public Double getStockChangePercent() { return stockChangePercent; }
    public Long getTimestamp() { return timestamp; }

    // Setters
    public void setStockName(String stockName) { this.stockName = stockName; }
    public void setStockTicker(String stockTicker) { this.stockTicker = stockTicker; }
    public void setStockPrice(Double stockPrice) { this.stockPrice = stockPrice; }
    public void setStockVolume(Double stockVolume) { this.stockVolume = stockVolume; }
    public void setStockChange(Double stockChange) { this.stockChange = stockChange; }
    public void setStockChangePercent(Double stockChangePercent) { this.stockChangePercent = stockChangePercent; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }
}
