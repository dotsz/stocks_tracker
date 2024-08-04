package com.victorfelipejr.stocks_tracker.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.RapidAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.textfield.TextField;


@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {

    private final RapidAPIService rapidAPIService;
    private final TextField tickerField;
    private final Button searchButton;
    private final Grid<Stock> stockGrid;



    @Autowired
    public MainView(RapidAPIService rapidAPIService) {
        this.rapidAPIService = rapidAPIService;

        tickerField = new TextField("Stock Ticker");
        searchButton = new Button("Search");
        stockGrid = new Grid<>(Stock.class);

        searchButton.addClickListener( e -> searchStock());

        add(tickerField, searchButton, stockGrid);


    }
    private void searchStock(){
        String ticker = tickerField.getValue();
        System.out.print(ticker);
        Stock stock = rapidAPIService.getStockQuotes(ticker);

        if(stock != null){

            stockGrid.setItems(stock);
            Notification.show("Stock found: " + stock.getStockName());
        }else {
            Notification.show("Error: Stock not found");
        }

    }

}



