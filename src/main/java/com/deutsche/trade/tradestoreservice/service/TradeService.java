package com.deutsche.trade.tradestoreservice.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.trade.tradestoreservice.exception.ValidationException;
import com.deutsche.trade.tradestoreservice.model.Trade;
import com.deutsche.trade.tradestoreservice.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;

	public List<Trade> getTrades() {
		return (List<Trade>) tradeRepository.findAll();
	}

	public List<Trade> storeAllTrades(@Valid List<Trade> trades) throws ValidationException, ParseException {
		List<Trade> responseTrades = new ArrayList<>();
		for(Trade trade:trades){
			responseTrades.add(storeSingleTrade(trade));
		}
		return responseTrades;
	}

	public Trade storeSingleTrade(@Valid Trade trade) throws ValidationException, ParseException {
		/*
		 * 2. Store should not allow the trade which has less maturity date then
		 * today date.
		 */
		trade.setCreatedDate(changeDateFormat(new Date()));
		trade.setMaturityDate(changeDateFormat(trade.getMaturityDate()));
		if (checkMaturityDateBelowToday(trade))
			throw new ValidationException("Maturity Date shouldn't be less than today's date");
		return (Trade) tradeRepository.save(trade);
	}

	public Trade modifySingleTrade(long id, @Valid Trade trade) throws ValidationException, ParseException {
		Trade tradeDetached = getTradeByTradeId(id);
		/*
		 * if the lower version is being received by the store it will reject
		 * the trade and throw an exception. If the version is same it will
		 * override the existing record.
		 */
		if (validateVersion(trade, tradeDetached))
			throw new ValidationException("Version shouldn't be lower than existing version");
		tradeDetached.setCounterPartyId(trade.getCounterPartyId());
		tradeDetached.setBookId(trade.getBookId());
		tradeDetached.setExpired(trade.getExpired());
		tradeDetached.setMaturityDate(changeDateFormat(trade.getMaturityDate()));
		if (checkMaturityDateBelowToday(trade))
			throw new ValidationException("Maturity Date shouldn't be less than today's date");
		tradeDetached.setVersion(trade.getVersion());
		return (Trade) tradeRepository.save(tradeDetached);
	}

	public Trade getTradeByTradeId(long id) {
		return tradeRepository.findById(id).get();
	}

	/*
	 * 3.	Store should automatically update the expire flag if in a store the trade 
	 * 		crosses the maturity date.
	 */
	
	public void updateExpireFlag() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
		List<Trade> trades = tradeRepository.getTradesMaturityLessThanToday();
		for(Trade trade:trades){
			trade.setExpired('Y');
			tradeRepository.save(trade);
		}
	}

	private boolean validateVersion(Trade trade, Trade tradeDetached) {
		return trade.getVersion() < tradeDetached.getVersion() ? true : false;
	}

	private Date changeDateFormat(Date date) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		return formatter1.parse(formatter1.format(formatter.parse(date.toString())));
	}

	private boolean checkMaturityDateBelowToday(Trade trade) throws ParseException {
		Date today = changeDateFormat(new Date());
		Date maturityDate = changeDateFormat(trade.getMaturityDate());
		return maturityDate.before(today) ? true : false;
	}
}
