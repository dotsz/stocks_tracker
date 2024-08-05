package com.victorfelipejr.stocks_tracker.services;

import com.vaadin.flow.component.notification.Notification;
import com.victorfelipejr.stocks_tracker.entities.Stock;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Service
public class RapidAPIService {


    @Value("${apikey_rapid}")
    private String rapidApiKey;

    public Stock getStockQuotes(String stockSymbol) {
        String apiURL = "https://twelve-data1.p.rapidapi.com/quote?symbol=" + stockSymbol + "&interval=1day&outputsize=30";

        // prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", "twelve-data1.p.rapidapi.com");

        // create request entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // make the GET request to the API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);

        try {
            // parse JSON response
            JSONObject quote = new JSONObject(response.getBody());

            // create Stock object
            String name = quote.optString("name", "N/A");
            String symbol = quote.optString("symbol", "N/A");
            Double currentPrice = quote.optDouble("open", 0.0);
            Double marketHigh = quote.optDouble("high", 0.0);
            Double marketLow = quote.optDouble("low", 0.0);

            //Debug
            long timestamp = quote.optLong("timestamp", 0);
            System.out.println("Timestamp: " + Date.from(new Date(timestamp).toInstant()));
            Notification.show("Timestamp: " + Date.from(new Date(timestamp).toInstant()), 5000, Notification.Position.MIDDLE);


            return new Stock(name, symbol, currentPrice, marketHigh, marketLow);
        }
        catch (Exception e) {
            Notification.show("Error: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            return null;
        }

    }

}
