package com.victorfelipejr.stocks_tracker.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.StockService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;


@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {


    private final StockService stockService;
    private Grid<Stock> grid;

    @Autowired
    public MainView(StockService stockService) {
        this.stockService = stockService;
        this.grid = new Grid(Stock.class);
        add(grid);
    }

    @PostConstruct
    public void init() {
        grid.setItems(stockService.getAllStocks());

        grid.removeColumnByKey("id");
        grid.setColumns("stockTicker", "stockPrice", "timestamp");


    }
}
