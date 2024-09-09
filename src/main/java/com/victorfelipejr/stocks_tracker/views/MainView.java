package com.victorfelipejr.stocks_tracker.views;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.victorfelipejr.stocks_tracker.entities.News;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import com.victorfelipejr.stocks_tracker.externalservice.RealTimeFinanceDataRapidApiService;
import com.victorfelipejr.stocks_tracker.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;


@Route("")
@PageTitle("Stocks Tracker")
@CssImport("./styles/styles.css")
public class MainView extends VerticalLayout {

    private final Grid<Stock> stockGrid;
    private final ListDataProvider<Stock> dataProvider;
    private final List<Stock> stockList;

    @Autowired
    public MainView(RealTimeFinanceDataRapidApiService realTimeFinanceDataRapidApiService, StockService stockService) {
        // Title
        Div title = new Div();
        title.setText("Stocks Tracker");
        title.addClassName("title");

        TextField stockName = new TextField("Stock Name");
        TextField stockSymbol = new TextField("Stock Symbol");
        TextField currentPrice = new TextField("Current Price");
        TextField marketHigh = new TextField("Market High");
        TextField marketLow = new TextField("Market Low");
        TextField lastUpdated = new TextField("Last Updated");
        TextField previousClose = new TextField("Previous Close");

        stockName.setReadOnly(true);
        stockSymbol.setReadOnly(true);
        currentPrice.setReadOnly(true);
        marketHigh.setReadOnly(true);
        marketLow.setReadOnly(true);
        lastUpdated.setReadOnly(true);
        previousClose.setReadOnly(true);

        stockName.setClassName("stock-name-field");
        stockSymbol.setClassName("stock-symbol-field");
        currentPrice.setClassName("current-price-field");
        marketHigh.setClassName("market-high-field");
        marketLow.setClassName("market-low-field");

        stockName.setMinWidth("100%");

        Div stockPricesContainer = new Div();
        stockPricesContainer.add(currentPrice, marketHigh, marketLow, previousClose, lastUpdated);

        // Stock Information
        VerticalLayout stockInformationPanel = new VerticalLayout();
        stockInformationPanel.add(stockSymbol, stockName, stockPricesContainer);
        stockName.setWidth("100%");
        stockInformationPanel.addClassName("stock-information");
        stockInformationPanel.setWidth("50vw");
        stockInformationPanel.getStyle().set("background-color", "#f4f5f7");
        stockInformationPanel.setMaxHeight("50%");

        // News Panel
        VerticalLayout newsPanel = new VerticalLayout();
        newsPanel.add(new Text("News Panel"));
        newsPanel.addClassName("news-panel");
        newsPanel.setWidth("50vw");
        newsPanel.setMaxHeight("50%");
        newsPanel.getStyle().set("background-color", "#f4f5f7");

        // VirtualList
        VirtualList<News> newsVirtualList = new VirtualList<>();

        newsVirtualList.setRenderer(new ComponentRenderer<>(news -> {
            VerticalLayout newsLayout = new VerticalLayout();
            newsLayout.setPadding(false);
            newsLayout.setSpacing(false);

            Image newsImage = new Image(news.getPhotoUrl(), news.getNewsTitle());
            newsImage.setMaxWidth("80%");
            newsLayout.add(newsImage);

            Paragraph newsTitle = new Paragraph(news.getNewsTitle());
            newsTitle.getStyle().set("font-weight", "bold");
            newsTitle.getStyle().set("font-size", "1.2em");
            newsLayout.add(newsTitle);

            Anchor newsUrl = new Anchor(news.getNewsUrl(), "Click to Read More...");
            newsUrl.setTarget("_blank");
            newsLayout.add(newsUrl);

            Paragraph newsSource = new Paragraph("Source: " + news.getNewsSource());
            newsLayout.add(newsSource);

            Paragraph newsPostedTime = new Paragraph("Posted on: " + news.getNewsPostedTime());
            newsLayout.add(newsPostedTime);

            return newsLayout;
        }));

        newsPanel.add(newsVirtualList);


        // Buttons
        TextField searchField = new TextField("", "Search Stock Symbol");
        Button searchButton = new Button("Search");
        Button addToWatchlist = new Button("Add to Watchlist");
        Button saveWatchList = new Button("Save Watchlist");

        // buttons layout
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(searchField, searchButton, addToWatchlist, saveWatchList);
        buttonsLayout.addClassName("buttons-layout");


        // Grid
        stockGrid = new Grid<>(Stock.class);
        stockGrid.setColumns("stockName", "stockSymbol", "currentPrice", "marketHigh", "marketLow", "previousClose","lastUpdated");
        stockGrid.addClassName("stock-grid");
        stockGrid.getStyle().set("Label", "Stocks Watchlist");
        stockGrid.getColumnByKey("lastUpdated").setAutoWidth(true);

        // Initialize the data provider
        stockList = new ArrayList<>(stockService.getAllStocks());
        dataProvider = new ListDataProvider<>(stockList);
        stockGrid.setDataProvider(dataProvider);
        Notification.show("Watchlist loaded", 5000, Notification.Position.MIDDLE);

        // Main Layout
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.add(stockInformationPanel, newsPanel);
        mainLayout.addClassName("main-layout");
        mainLayout.getStyle().set("padding", "1em");
        mainLayout.getStyle().set("width", "100%");

        // Add components to the view
        add(title, buttonsLayout, mainLayout, stockGrid);

        // Event Listeners
        searchButton.addClickListener( event -> {
            String symbol = searchField.getValue();

//            List<News> newsList = realTimeFinanceDataRapidApiService.getStockNewsData(symbol);

            try {
                Stock stockFromApi = realTimeFinanceDataRapidApiService.getStockData(symbol);
                if (stockFromApi != null) {
                    stockName.setValue(stockFromApi.getStockName());
                    stockSymbol.setValue(stockFromApi.getStockSymbol());
                    currentPrice.setValue(stockFromApi.getCurrentPrice().toString());
                    marketHigh.setValue(stockFromApi.getMarketHigh().toString());
                    marketLow.setValue(stockFromApi.getMarketLow().toString());
                    previousClose.setValue(stockFromApi.getPreviousClose().toString());
                    lastUpdated.setValue(stockFromApi.getLastUpdated());
                }

//                if(newsList != null){
//                    newsVirtualList.setItems(newsList);
//                }

            } catch (HttpClientErrorException.TooManyRequests e){
                Notification.show(String.valueOf(e), 10000, Notification.Position.MIDDLE);

            }

            catch (Exception e){
                Notification.show("Error: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });

        addToWatchlist.addClickListener( event -> {
            String symbol = stockSymbol.getValue();
            Stock existingStock = stockList.stream().filter(stock -> stock.getStockSymbol().equals(symbol)).findFirst().orElse(null);

            try {
                if (existingStock != null) {
                    existingStock.setStockName(stockName.getValue());
                    existingStock.setStockSymbol(stockSymbol.getValue());
                    existingStock.setCurrentPrice(Double.parseDouble(currentPrice.getValue()));
                    existingStock.setMarketHigh(Double.parseDouble(marketHigh.getValue()));
                    existingStock.setMarketLow(Double.parseDouble(marketLow.getValue()));
                    existingStock.setPreviousClose(Double.parseDouble(previousClose.getValue()));
                    existingStock.setLastUpdated(lastUpdated.getValue());

                    dataProvider.refreshItem(existingStock);
                    Notification.show("Stock updated in the watchlist", 5000, Notification.Position.MIDDLE);
                } else {
                    Stock stock = new Stock();
                    stock.setStockName(stockName.getValue());
                    stock.setStockSymbol(stockSymbol.getValue());
                    stock.setCurrentPrice(Double.parseDouble(currentPrice.getValue()));
                    stock.setMarketHigh(Double.parseDouble(marketHigh.getValue()));
                    stock.setMarketLow(Double.parseDouble(marketLow.getValue()));
                    stock.setPreviousClose(Double.parseDouble(previousClose.getValue()));
                    stock.setLastUpdated(lastUpdated.getValue());

                    stockList.add(stock);
                    dataProvider.refreshAll();
                    Notification.show("Stock added to the watchlist", 5000, Notification.Position.MIDDLE);
                }
            } catch (Exception e){
                Notification.show("Error: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            }
        });

        saveWatchList.addClickListener( event -> {
            stockService.saveAllStocks(stockList);
            Notification.show("Watchlist saved", 5000, Notification.Position.MIDDLE);
        });



    }
}



