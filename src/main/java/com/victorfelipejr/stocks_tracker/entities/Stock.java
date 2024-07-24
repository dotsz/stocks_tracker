package com.victorfelipejr.stocks_tracker.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    private String stockName;
    private String stock_ticker;
    private Double stock_price;
    private String timestamp;

    public Stock(){}


    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
//    public String getStockName(){
//        return stockName;
//    }
//    public void setStockName(String stockName){
//        this.stockName = stockName;
//    }
    public String getStockTicker(){
        return stock_ticker;
    }
    public void setStockTicker(String stock_ticker){
        this.stock_ticker = stock_ticker;
    }
    public Double getStockPrice(){
        return stock_price;
    }
    public void setStockPrice(Double stock_price){
        this.stock_price = stock_price  ;
    }

    public String getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
}