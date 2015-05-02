package com.greenation.bigstock.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.greenation.bigstock.domain.StockTradingProfit;


public class CSVOutputTest {

	@Test
	public void testCSVOutput(){
		StockTradingProfit stp = new StockTradingProfit();
		stp.setCode("00133");
		stp.setName("HELLO");
		stp.setQuantity(new BigDecimal(3000));
		stp.setBuyPrice(new BigDecimal(2));;
		stp.setSellPrice(new BigDecimal(5));
		stp.setBuyTransactionFee(new BigDecimal(30));
		stp.setSellTransactionFee(new BigDecimal(50));
		stp.setBuyConsideration(new BigDecimal(6000));
		stp.setSellConsideration(new BigDecimal(15000));
		stp.setCurrency("HKD");
		stp.setProfit(new BigDecimal(8920));
		stp.setMarket("Hong Kong");
		
		List<String> header = StockTradingProfit.getCSVHeader();
		Bean2CSVService csvService = new Bean2CSVService();
		csvService.setHeader(header);
		csvService.setFilePath("myCSV.csv");
		csvService.setup(StockTradingProfit.class);
		csvService.write(stp);
	}
}
