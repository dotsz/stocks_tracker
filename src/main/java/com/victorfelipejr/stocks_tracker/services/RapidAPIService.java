package com.victorfelipejr.stocks_tracker.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import com.victorfelipejr.stocks_tracker.entities.Stock;

import java.util.Date;

@Service
public class RapidAPIService {

    @Value("${api.rapid.key}")
    private String rapidApiKey;

    private final String apiURL = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes";

    public Stock fetchStockInfo(String stockTicker) {
        String url = String.format("%s?region=US&symbols=%s", apiURL, stockTicker);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Host", "apidojo-yahoo-finance-v1.p.rapidapi.com");
        headers.set("X-RapidAPI-Key", rapidApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject quoteResponse = jsonObject.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0);

        String ticker = quoteResponse.getString("symbol");
        double price = quoteResponse.getDouble("regularMarketPrice");

        Stock stock = new Stock();
        stock.setStockTicker(ticker);
        stock.setStockPrice(price);
        stock.setTimestamp(String.valueOf(new Date()));


        return stock;
    }
}
