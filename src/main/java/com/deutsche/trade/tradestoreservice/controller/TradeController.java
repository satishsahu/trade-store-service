package com.deutsche.trade.tradestoreservice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.trade.tradestoreservice.exception.ValidationException;
import com.deutsche.trade.tradestoreservice.model.Trade;
import com.deutsche.trade.tradestoreservice.service.TradeService;


@RestController
public class TradeController {

	@Autowired
	TradeService tradeService;
	
	@GetMapping("/trades")
	public ResponseEntity<List<Trade>> getTrades(){
		return new ResponseEntity<List<Trade>>(tradeService.getTrades(), HttpStatus.OK);
	}
	
	@PostMapping("/trades")
	public ResponseEntity<List<Trade>> storeAllTrades(@RequestBody List<Trade> trades) throws ValidationException, ParseException{
		return new ResponseEntity<List<Trade>>(tradeService.storeAllTrades(trades), HttpStatus.CREATED);
	}
	
	@PostMapping("/trade")
	public ResponseEntity<Trade> storeSingleTrade(@RequestBody Trade trade) throws ValidationException, ParseException{
		return new ResponseEntity<Trade>(tradeService.storeSingleTrade(trade), HttpStatus.CREATED);
	}
	
	@GetMapping("/trades/{id}")
	public ResponseEntity<Trade> getTradeByTradeId(@PathVariable long id){
		return new ResponseEntity<Trade>(tradeService.getTradeByTradeId(id), HttpStatus.OK);
	}
	
	@PutMapping("/trade/{id}")
	public ResponseEntity<Trade> modifySingleTrade(@PathVariable long id, @RequestBody Trade trade) throws ValidationException, ParseException {
		return new ResponseEntity<Trade>(tradeService.modifySingleTrade(id, trade), HttpStatus.OK);
	}
}
