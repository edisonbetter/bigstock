package com.greenation.bigstock.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FundTradingProfit.
 */
@Entity
@Table(name = "T_FUND_TRADING_PROFIT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FundTradingProfit extends TradingProfit implements Serializable{
	@NotNull
    @Column(name = "country", nullable = false)
    private String country;
	
	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                ", market='" + getCountry() + "'" +
                ", username='" + getUsername() + "'" +
                '}';
    }
}
