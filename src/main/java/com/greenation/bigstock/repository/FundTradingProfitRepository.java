package com.greenation.bigstock.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenation.bigstock.domain.FundTradingProfit;
import com.greenation.bigstock.domain.StockTradingProfit;

public interface FundTradingProfitRepository extends JpaRepository<FundTradingProfit, Long>{
	
	@Query("SELECT fundTradingProfit FROM FundTradingProfit fundTradingProfit WHERE fundTradingProfit.username = ?#{principal.username}")
    List<StockTradingProfit> findAllForCurrentUser();
	
	@Query("SELECT fundTradingProfit FROM FundTradingProfit fundTradingProfit WHERE fundTradingProfit.username = ?#{principal.username}")
    Page<FundTradingProfit> findAll(Pageable pageable);
	
	@Query("SELECT fundTradingProfit FROM FundTradingProfit fundTradingProfit WHERE fundTradingProfit.username = ?#{principal.username} and currency=:currency")
    Page<FundTradingProfit> findByCurrency(@Param("currency")String currency, Pageable pageable);
}
