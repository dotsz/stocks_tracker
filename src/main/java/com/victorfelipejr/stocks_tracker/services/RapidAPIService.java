package com.victorfelipejr.stocks_tracker.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import com.victorfelipejr.stocks_tracker.entities.Stock;

import java.util.Date;

@Service
public class RapidAPIService {

    @Value("${api.rapid.key}")
    private String rapidApiKey;


    public Stock fetchStockInfo(String stockTicker) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", rapidApiKey);
        headers.set("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        String url = "https://apidojo-yahoo-finance-v1.p.rapidapi.com/market/v2/get-quotes?symbols=" + stockTicker;

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONObject quote = jsonObject.getJSONArray("quoteResponse").getJSONObject(0);
            Stock stock = new Stock();
            stock.setStockTicker(quote.getString("symbol"));
            stock.setStockPrice(quote.getDouble("regularMarketPrice"));
            stock.setTimestamp(new Date().toString());
            return stock;
        } catch (HttpClientErrorException e) {
            return null;

        }
    }
}
