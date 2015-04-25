package com.greenation.bigstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greenation.bigstock.domain.TotalProfit;

public interface TotalProfitRepository extends JpaRepository<TotalProfit, Long> {

	@Query("SELECT totalProfit FROM TotalProfit totalProfit WHERE totalProfit.username = ?#{principal.username}")
    TotalProfit getByUser();
}
