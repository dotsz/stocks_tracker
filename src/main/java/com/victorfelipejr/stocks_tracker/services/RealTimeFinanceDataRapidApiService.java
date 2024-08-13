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


@Service
public class RealTimeFinanceDataRapidApiService {

    @Value("${apikey_rapid}")
    private String rapidApiKey;

    public Stock getData(String stockSymbol) {
        String apiURL = "https://real-time-finance-data.p.rapidapi.com/stock-quote?symbol=" + stockSymbol + "&language=en HTTP/1.1";

        // prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", "real-time-finance-data.p.rapidapi.com");

        // create request entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // make the GET request to the API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);

        try {
            // parse JSON response
            JSONObject jsonResponse = new JSONObject(response.getBody());
            JSONObject dataObject = jsonResponse.getJSONObject("data");


            // create Stock object
            String name = dataObject.optString("name", "N/A");
            String symbol = dataObject.optString("symbol", "N/A");
            Double currentPrice = dataObject.optDouble("price", 0.0);
            Double marketHigh = dataObject.optDouble("high", 0.0);
            Double marketLow = dataObject.optDouble("low", 0.0);
            Double previousClose = dataObject.optDouble("previous_close", 0.0);
            String lastUpdated = dataObject.optString("last_update_utc", "N/A");


            return new Stock(name, symbol, currentPrice, marketHigh, marketLow, previousClose, lastUpdated);
        }
        catch (Exception e) {
            Notification.show("Error: " + e.getMessage(), 5000, Notification.Position.MIDDLE);
            return null;
        }

    }

}
