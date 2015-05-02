package com.greenation.bigstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.greenation.bigstock.domain.util.CustomLocalDateSerializer;
import com.greenation.bigstock.domain.util.ISO8601LocalDateDeserializer;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A StockTradingProfit.
 */
@Entity
@Table(name="STOCK_TRADING_PROFIT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Record
public class StockTradingProfit extends TradingProfit implements Serializable {

	@NotNull
    @Column(name = "market", nullable = false)
	@Field(at=11)
    private String market;
	
	public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

	public static List<String> getCSVHeader() {
    	List<String> header = TradingProfit.getCSVHeader();
    	header.add("market");
		return header;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StockTradingProfit stockTradingProfit = (StockTradingProfit) o;

        if ( ! Objects.equals(getId(), stockTradingProfit.getId())) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StockTradingProfit{" +
                "id=" + getId() +
                ", creationDate='" + getCreationDate() + "'" +
                ", code='" + getCode() + "'" +
                ", name='" + getName() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", buyPrice='" + getBuyPrice() + "'" +
                ", sellPrice='" + getSellPrice() + "'" +
                ", buyTransactionFee='" + getBuyTransactionFee() + "'" +
                ", sellTransactionFee='" + getSellTransactionFee() + "'" +
                ", buyConsideration='" + getBuyConsideration() + "'" +
                ", sellConsideration='" + getSellConsideration() + "'" +
                ", profit='" + getProfit() + "'" +
                ", currency='" + getCurrency() + "'" +
                ", market='" + getMarket() + "'" +
                ", username='" + getUsername() + "'" +
                '}';
    }
}
