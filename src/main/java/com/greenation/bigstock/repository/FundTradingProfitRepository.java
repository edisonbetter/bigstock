package com.greenation.bigstock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenation.bigstock.domain.FundTradingProfit;

public interface FundTradingProfitRepository extends JpaRepository<FundTradingProfit, Long>{
	
	@Query("SELECT fundTradingProfit FROM FundTradingProfit fundTradingProfit WHERE fundTradingProfit.username = ?#{principal.username}")
    Page<FundTradingProfit> findAll(Pageable pageable);
	
	@Query("SELECT fundTradingProfit FROM FundTradingProfit fundTradingProfit WHERE fundTradingProfit.username = ?#{principal.username} and currency=:currency")
    Page<FundTradingProfit> findByCurrency(@Param("currency")String currency, Pageable pageable);
}
