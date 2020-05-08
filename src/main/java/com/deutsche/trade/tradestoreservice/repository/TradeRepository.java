package com.deutsche.trade.tradestoreservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deutsche.trade.tradestoreservice.model.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {

	@Query(value = "select t from Trade t where t.maturityDate < CURRENT_DATE()")
	public List<Trade> getTradesMaturityLessThanToday();
}
