package com.deutsche.trade.tradestoreservice;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deutsche.trade.tradestoreservice.model.Trade;

public class TradeStoreServiceApplicationTests extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	/*
	 * @Autowired private TradeService libraryService;
	 */

	@Test
	public void getProductsList() throws Exception {
		String uri = "/trades";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Trade[] productlist = super.mapFromJson(content, Trade[].class);
//		assertTrue(productlist.length > 0);
	}

	@Test
	public void storeTradeCorrectly() throws Exception {
		String uri = "/trade";
		Trade trade = new Trade(1, 1, "CP1", "B1", new Date("08/05/2020"), new Date(), 'N');

		String inputJson = super.mapToJson(trade);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Trade is created successfully");
	}

	/*
	 * @Test public void
	 * whenTradeIdIsProvided_thenRetrievedCounterPartyIdIsCorrect() { String
	 * testName = libraryService.getTradeByTradeId(1).getCounterPartyId();
	 * Assert.assertEquals("CP1", testName); }
	 */

}
