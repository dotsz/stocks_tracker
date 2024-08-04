package com.victorfelipejr.stocks_tracker.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.RapidAPIService;
import org.springframework.beans.factory.annotation.Autowired;



@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {

    private final RapidAPIService rapidAPIService;
    private final TextField stockSymbol = new TextField("Stock Symbol");
    private final Grid<Stock> stockGrid;

    @Autowired
    public MainView(RapidAPIService rapidAPIService) {
        this.rapidAPIService = rapidAPIService;

        Button searchButton = new Button("Search");
        stockGrid =  new Grid<>(Stock.class);
        stockGrid.setColumns("stockName", "stockSymbol", "currentPrice", "marketHigh", "marketLow", "fifthyDayAverage", "currentDate");

        searchButton.addClickListener( e -> {
            String symbol = stockSymbol.getValue();
            Stock stock = rapidAPIService.getStockQuotes(symbol);

            try{
               if (stock != null){
                   stockGrid.setItems(stock);
               } else {
                   stockGrid.setItems();
                   Notification.show("Stock not found");
               }
            } catch (Exception exception){
                Notification.show("Error: " + exception.getMessage());
            }

        });
        add(stockGrid, stockSymbol, searchButton);

    }
}



