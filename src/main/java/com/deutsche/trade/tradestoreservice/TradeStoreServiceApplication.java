package com.deutsche.trade.tradestoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeStoreServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeStoreServiceApplication.class, args);
	}

}
