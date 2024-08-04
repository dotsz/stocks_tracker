package com.victorfelipejr.stocks_tracker;

import com.victorfelipejr.stocks_tracker.services.RapidAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StocksTrackerApplication {


	public static void main(String[] args) {
		SpringApplication.run(StocksTrackerApplication.class, args);
	}

}
