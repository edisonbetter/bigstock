package com.greenation.bigstock.repository;

import com.greenation.bigstock.domain.StockTradingProfit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StockTradingProfit entity.
 */
public interface StockTradingProfitRepository extends JpaRepository<StockTradingProfit,Long> {

    @Query("select stockTradingProfit from StockTradingProfit stockTradingProfit where stockTradingProfit.username = ?#{principal.username}")
    List<StockTradingProfit> findAllForCurrentUser();

    @Query("select stockTradingProfit from StockTradingProfit stockTradingProfit where stockTradingProfit.username = ?#{principal.username}")
    Page<StockTradingProfit> findAll(Pageable pageable);
}
