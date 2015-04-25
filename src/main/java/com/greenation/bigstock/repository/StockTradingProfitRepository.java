package com.greenation.bigstock.repository;

import com.greenation.bigstock.domain.StockTradingProfit;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the StockTradingProfit entity.
 */
public interface StockTradingProfitRepository extends JpaRepository<StockTradingProfit,Long> {

    @Query("select stockTradingProfit from StockTradingProfit stockTradingProfit where stockTradingProfit.username = ?#{principal.username}")
    List<StockTradingProfit> findAllForCurrentUser();

    @Query("SELECT stockTradingProfit FROM StockTradingProfit stockTradingProfit WHERE stockTradingProfit.username = ?#{principal.username}")
    Page<StockTradingProfit> findAll(Pageable pageable);
    
    @Query("SELECT stockTradingProfit FROM StockTradingProfit stockTradingProfit WHERE stockTradingProfit.username = ?#{principal.username} and currency=:currency")
    Page<StockTradingProfit> findByCurrency(@Param("currency")String currency, Pageable pageable);
    
}
