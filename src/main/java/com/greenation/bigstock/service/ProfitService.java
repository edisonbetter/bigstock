package com.greenation.bigstock.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenation.bigstock.domain.StockTradingProfit;
import com.greenation.bigstock.domain.TotalProfit;
import com.greenation.bigstock.repository.FundTradingProfitRepository;
import com.greenation.bigstock.repository.StockTradingProfitRepository;
import com.greenation.bigstock.repository.TotalProfitRepository;
import com.greenation.bigstock.security.SecurityUtils;

@Service
@Transactional
public class ProfitService {

	private final Logger log = LoggerFactory.getLogger(ProfitService.class);
	
	@Inject
	private TotalProfitRepository totalProfitRepository;
	
	public TotalProfit getTotalProfits(){
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		return totalProfit;
	}
	
	public void updateTotalProfits(String currency, BigDecimal profit){
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		if(null == totalProfit){
			totalProfit = createTotalProfit();
		}
		
		switch(currency){
			case "CNY": addToCNYTotalProfit(totalProfit, profit);
						break;
			case "HKD": addToHKDTotalProfit(totalProfit, profit);
						break;
			default: 	break;
		}
			 
		totalProfitRepository.save(totalProfit);
	}

	private void addToCNYTotalProfit(TotalProfit totalProfit, BigDecimal profit){
		BigDecimal cnyProfits = totalProfit.getCnyProfits();
		cnyProfits = cnyProfits.add(profit);
		totalProfit.setCnyProfits(cnyProfits);
	}
	
	private void addToHKDTotalProfit(TotalProfit totalProfit, BigDecimal profit){
		BigDecimal hkdProfits = totalProfit.getHkdProfits();
		hkdProfits = hkdProfits.add(profit);
		totalProfit.setHkdProfits(hkdProfits);
	}
	
	private TotalProfit createTotalProfit(){
		TotalProfit totalProfit = new TotalProfit();
		totalProfit.setUsername(SecurityUtils.getCurrentLogin());
		totalProfit.setHkdProfits(BigDecimal.ZERO);
		totalProfit.setCnyProfits(BigDecimal.ZERO);
		log.debug("created new TotalProfit for user " + SecurityUtils.getCurrentLogin());
		return totalProfit;
	}

}
