package com.victorfelipejr.stocks_tracker.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;




@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {


    @Autowired
    public MainView(StockService stockService) {
        Grid<Stock> grid = new Grid<>(Stock.class);
        grid.setItems(stockService.getAllStocks());
        grid.removeColumnByKey("id");
        grid.setColumns("stockTicker", "stockPrice", "timestamp");

        // placeholder

        Input searchInput = new Input();
        Button searchButton = new Button("Search");
//        searchButton.addClickListener(e -> {
//            grid.setItems(stockService.getAllStocks(searchInput.getValue()));
//        });

        add(searchInput, searchButton, grid);


    }

}



