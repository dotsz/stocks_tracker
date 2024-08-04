package com.victorfelipejr.stocks_tracker.services;

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
public class RapidAPIService {


    @Value("${apikey_rapid}")
    private String rapidApiKey;

    public Stock getStockQuotes(String stockSymbol) {
        String apiURL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?symbols=" + stockSymbol;

        // prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");

        // create request entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // make the GET request to the API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;

        response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);


        // parse JSON response
        JSONObject jsonResponse = new JSONObject(response.getBody());
        JSONObject quote = jsonResponse.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0);

        String name = quote.optString("longName", "N/A");
        String symbol = quote.optString("symbol", "N/A");
        Double currentPrice = quote.optDouble("regularMarketPrice", 0.0);
        Double marketHigh = quote.optDouble("regularMarketDayHigh", 0.0);
        Double marketLow = quote.optDouble("regularMarketDayLow", 0.0);
        Double average = quote.optDouble("fiftyDayAverage", 0.0);
        String currentDate = quote.optString("regularMarketTime", "N/A");

        return new Stock(name, symbol, currentPrice, marketHigh, marketLow, average, currentDate);


    }

}
