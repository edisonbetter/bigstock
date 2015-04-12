package com.greenation.bigstock.repository;

import com.greenation.bigstock.domain.StockTradingProfit;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StockTradingProfit entity.
 */
public interface StockTradingProfitRepository extends JpaRepository<StockTradingProfit,Long> {

    @Query("select stockTradingProfit from StockTradingProfit stockTradingProfit where stockTradingProfit.user.login = ?#{principal.username}")
    List<StockTradingProfit> findAllForCurrentUser();

}
