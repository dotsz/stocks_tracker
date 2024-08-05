package com.victorfelipejr.stocks_tracker.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.services.RapidAPIService;
import org.springframework.beans.factory.annotation.Autowired;


//@CssImport("./styles/styles.css")
@Route("")
@PageTitle("Stocks Tracker")
public class MainView extends VerticalLayout {

    private final TextField stockSymbol = new TextField("Stock Symbol");
    private final Grid<Stock> stockGrid;

    @Autowired
    public MainView(RapidAPIService rapidAPIService) {

        stockGrid =  new Grid<>(Stock.class);
        stockGrid.setColumns("stockName", "stockSymbol", "currentPrice", "marketHigh", "marketLow");

        // create UI components
        H1 title = new H1("Stocks Tracker");
        title.getStyle().set("text-align", "center");
        title.getStyle().set("font-size", "2em");
        title.getStyle().set("margin", "0 auto");
        add(title);

        Button searchButton = new Button("Search");
        searchButton.addClickListener(_ -> {
            String symbol = stockSymbol.getValue();
            Stock stock = rapidAPIService.getStockQuotes(symbol);



            try{
                if (stock != null){
                    stockGrid.setItems(stock);
                } else {
                    stockGrid.setItems();
                }
            } catch (Exception exception){
                Notification.show("Error: " + exception.getMessage());
            }

        });

        Button saveButton = new Button("Save to Watchlist");

        HorizontalLayout buttonsLayout = new HorizontalLayout(searchButton, saveButton);
        buttonsLayout.setSpacing(true);

        add(stockSymbol, buttonsLayout, stockGrid);

    }
}



