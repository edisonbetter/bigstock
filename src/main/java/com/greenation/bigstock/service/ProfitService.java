package com.greenation.bigstock.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greenation.bigstock.domain.TotalProfit;
import com.greenation.bigstock.domain.TradingProfit;
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

	@Inject
	private StockTradingProfitRepository stockTradingProfitRepository;

	@Inject
	private FundTradingProfitRepository fundTradingProfitRepository;

	public TotalProfit getTotalProfits() {
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		return totalProfit;
	}
	
	public void addToTotalProfits(String securityType, TradingProfit tradingProfit){
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		if (null == totalProfit) {
			totalProfit = createTotalProfit();
		}
		
		String currency = tradingProfit.getCurrency();
		BigDecimal profit = tradingProfit.getProfit();	
		updateTotalProfits(securityType, currency, totalProfit, profit);
	}

	public void updateTotalProfits(String securityType,
			TradingProfit tradingProfit) {
		TotalProfit totalProfit = totalProfitRepository.getByUser();
		String currency = tradingProfit.getCurrency();
		BigDecimal profit = tradingProfit.getProfit();
		TradingProfit existing = null;

		switch (securityType) {
		case "Fund":
			existing = fundTradingProfitRepository.findOne(tradingProfit
					.getId());
			break;
		case "Stock":
			existing = stockTradingProfitRepository.findOne(tradingProfit
					.getId());
			break;
		default:
			break;
		}

		BigDecimal existingProfit = existing.getProfit();
		BigDecimal currentProfit = tradingProfit.calculateProfit();
		if (!existingProfit.equals(currentProfit)) {
			profit = currentProfit.subtract(existingProfit);
		}
		updateTotalProfits(securityType, currency, totalProfit, profit);
	}
	
	private void updateTotalProfits(String securityType, String currency, TotalProfit totalProfit, BigDecimal profitToAdd){
		switch (currency) {
		case "CNY":
			addToCNYTotalProfit(securityType, totalProfit, profitToAdd);
			break;
		case "HKD":
			addToHKDTotalProfit(securityType, totalProfit, profitToAdd);
			break;
		default:
			break;
		}
		totalProfitRepository.save(totalProfit);
	}

	private void addToCNYTotalProfit(String securityType,
			TotalProfit totalProfit, BigDecimal profit) {
		BigDecimal cnyProfits = totalProfit.getCnyProfits();
		cnyProfits = cnyProfits.add(profit);
		totalProfit.setCnyProfits(cnyProfits);

		BigDecimal cnySecurityProfit = BigDecimal.ZERO;
		switch (securityType) {
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

	private void addToHKDTotalProfit(String securityType,
			TotalProfit totalProfit, BigDecimal profit) {
		BigDecimal hkdProfits = totalProfit.getHkdProfits();
		hkdProfits = hkdProfits.add(profit);
		totalProfit.setHkdProfits(hkdProfits);

		BigDecimal hkdSecurityProfit = BigDecimal.ZERO;
		switch (securityType) {
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

	private TotalProfit createTotalProfit() {
		TotalProfit totalProfit = new TotalProfit();
		totalProfit.setUsername(SecurityUtils.getCurrentLogin());
		totalProfit.setHkdProfits(BigDecimal.ZERO);
		totalProfit.setHkdFundProfits(BigDecimal.ZERO);
		totalProfit.setHkdStockProfits(BigDecimal.ZERO);
		totalProfit.setCnyProfits(BigDecimal.ZERO);
		totalProfit.setCnyFundProfits(BigDecimal.ZERO);
		totalProfit.setCnyStockProfits(BigDecimal.ZERO);
		log.debug("created new TotalProfit for user "
				+ SecurityUtils.getCurrentLogin());
		return totalProfit;
	}

}
