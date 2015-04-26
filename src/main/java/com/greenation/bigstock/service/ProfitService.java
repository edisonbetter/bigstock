package com.greenation.bigstock.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenation.bigstock.domain.TotalProfit;
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
	
	public void updateTotalProfits(String securityType, String currency, BigDecimal profit){
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		if(null == totalProfit){
			totalProfit = createTotalProfit();
		}
		
		switch(currency){
			case "CNY": addToCNYTotalProfit(securityType, totalProfit, profit);
						break;
			case "HKD": addToHKDTotalProfit(securityType, totalProfit, profit);
						break;
			default: 	break;
		}
			 
		totalProfitRepository.save(totalProfit);
	}

	private void addToCNYTotalProfit(String securityType, TotalProfit totalProfit, BigDecimal profit){
		BigDecimal cnyProfits = totalProfit.getCnyProfits();
		cnyProfits = cnyProfits.add(profit);
		totalProfit.setCnyProfits(cnyProfits);
		
		BigDecimal cnySecurityProfit = BigDecimal.ZERO;
		switch(securityType){
			case "Fund": 
				cnySecurityProfit = totalProfit.getCnyFundProfits().add(profit);
				totalProfit.setCnyFundProfits(cnySecurityProfit);
				break;
			case "Stock": 
				cnySecurityProfit = totalProfit.getCnyStockProfits().add(profit);
				totalProfit.setCnyStockProfits(cnySecurityProfit);
				break;
			default: 
				break;
		}
		
	}
	
	private void addToHKDTotalProfit(String securityType, TotalProfit totalProfit, BigDecimal profit){
		BigDecimal hkdProfits = totalProfit.getHkdProfits();
		hkdProfits = hkdProfits.add(profit);
		totalProfit.setHkdProfits(hkdProfits);
		
		BigDecimal hkdSecurityProfit = BigDecimal.ZERO;
		switch(securityType){
			case "Fund": 
				hkdSecurityProfit = totalProfit.getHkdFundProfits().add(profit);
				totalProfit.setHkdFundProfits(hkdSecurityProfit);
				break;
			case "Stock": 
				hkdSecurityProfit = totalProfit.getHkdStockProfits().add(profit);
				totalProfit.setHkdStockProfits(hkdSecurityProfit);
				break;
			default: 
				break;
		}
	}
	
	private TotalProfit createTotalProfit(){
		TotalProfit totalProfit = new TotalProfit();
		totalProfit.setUsername(SecurityUtils.getCurrentLogin());
		totalProfit.setHkdProfits(BigDecimal.ZERO);
		totalProfit.setHkdFundProfits(BigDecimal.ZERO);
		totalProfit.setHkdStockProfits(BigDecimal.ZERO);
		totalProfit.setCnyProfits(BigDecimal.ZERO);
		totalProfit.setCnyFundProfits(BigDecimal.ZERO);
		totalProfit.setCnyStockProfits(BigDecimal.ZERO);
		log.debug("created new TotalProfit for user " + SecurityUtils.getCurrentLogin());
		return totalProfit;
	}

}
