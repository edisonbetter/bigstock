package com.greenation.bigstock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenation.bigstock.domain.FundTradingProfit;

public interface FundTradingProfitRepository extends JpaRepository<FundTradingProfit, Long>{
	
	@Query("select fundTradingProfit from FundTradingProfit fundTradingProfit where fundTradingProfit.username = ?#{principal.username}")
    Page<FundTradingProfit> findAll(Pageable pageable);
}
