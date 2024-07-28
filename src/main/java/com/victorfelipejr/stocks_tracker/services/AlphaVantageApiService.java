package com.victorfelipejr.stocks_tracker.services;

import com.victorfelipejr.stocks_tracker.entities.Stock;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlphaVantageApiService {

    @Value("${api.alpha_vantage.key}")
    private String alPhaVantageApiKey;

    private final String alphaVantageApiURL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=5min&apikey=%s";;


    public List<Stock> fetchStocks(String stock_ticker) {
        // Create the URL for the API request
        String url = String.format(alphaVantageApiURL, stock_ticker, alPhaVantageApiKey);

        // Make a GET request to the API
        RestTemplate restTemplate= new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);


        // for Debug
        System.out.println("API Response: " + response);

        // Parse the response and return a list of Stock objects
        List<Stock> stocks = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(response);

        if(jsonObject.has("Time Series (5min)")){

            try {
                JSONObject timeSeries = jsonObject.getJSONObject("Time Series (5min)");

                int count_limit = 0;
                for (String timestamp : timeSeries.keySet()) {

                    if(count_limit == 5){
                        break;
                    }

                    JSONObject stockData = timeSeries.getJSONObject(timestamp);
                    Double price = stockData.getDouble("4. close");

                    Stock stock = new Stock();
                    stock.setStockTicker(stock_ticker);
                    stock.setStockPrice(price);
                    stock.setTimestamp(timestamp);
                    stocks.add(stock);

                    count_limit++;
                }
            } catch (Exception e) {
                e.printStackTrace();
        }
        }

        return stocks;
    }

}
