package com.deutsche.trade.tradestoreservice.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutsche.trade.tradestoreservice.service.TradeService;

@Component
public class JobScheduler {

	@Autowired
	TradeService tradeService;

	@Scheduled(cron = "0 * * ? * *")
	public void cronJobSch() {
		tradeService.updateExpireFlag();
	}
}
