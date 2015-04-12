package com.greenation.bigstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.greenation.bigstock.domain.util.CustomLocalDateSerializer;
import com.greenation.bigstock.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A StockTradingProfit.
 */
@Entity
@Table(name = "T_STOCKTRADINGPROFIT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StockTradingProfit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "quantity", precision=10, scale=2, nullable = false)
    private BigDecimal quantity;

    @NotNull
    @Column(name = "buy_price", precision=10, scale=2, nullable = false)
    private BigDecimal buyPrice;

    @NotNull
    @Column(name = "sell_price", precision=10, scale=2, nullable = false)
    private BigDecimal sellPrice;

    @NotNull
    @Column(name = "buy_transaction_fee", precision=10, scale=2, nullable = false)
    private BigDecimal buyTransactionFee;

    @NotNull
    @Column(name = "sell_transaction_fee", precision=10, scale=2, nullable = false)
    private BigDecimal sellTransactionFee;

    @Column(name = "buy_consideration", precision=10, scale=2, nullable = false)
    private BigDecimal buyConsideration;

    @Column(name = "sell_consideration", precision=10, scale=2, nullable = false)
    private BigDecimal sellConsideration;

    @NotNull
    @Column(name = "profit", precision=10, scale=2, nullable = false)
    private BigDecimal profit;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "market", nullable = false)
    private String market;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getBuyTransactionFee() {
        return buyTransactionFee;
    }

    public void setBuyTransactionFee(BigDecimal buyTransactionFee) {
        this.buyTransactionFee = buyTransactionFee;
    }

    public BigDecimal getSellTransactionFee() {
        return sellTransactionFee;
    }

    public void setSellTransactionFee(BigDecimal sellTransactionFee) {
        this.sellTransactionFee = sellTransactionFee;
    }

    public BigDecimal getBuyConsideration() {
        return buyConsideration;
    }

    public void setBuyConsideration(BigDecimal buyConsideration) {
        this.buyConsideration = buyPrice.multiply(quantity).subtract(buyTransactionFee);
    }

    public BigDecimal getSellConsideration() {
        return sellConsideration;
    }

    public void setSellConsideration(BigDecimal sellConsideration) {
        this.sellConsideration = sellPrice.multiply(quantity).subtract(sellTransactionFee);;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = sellPrice.subtract(buyPrice).multiply(quantity).subtract(buyTransactionFee).subtract(sellTransactionFee);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

        if ( ! Objects.equals(id, stockTradingProfit.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StockTradingProfit{" +
                "id=" + id +
                ", creationDate='" + creationDate + "'" +
                ", code='" + code + "'" +
                ", name='" + name + "'" +
                ", quantity='" + quantity + "'" +
                ", buyPrice='" + buyPrice + "'" +
                ", sellPrice='" + sellPrice + "'" +
                ", buyTransactionFee='" + buyTransactionFee + "'" +
                ", sellTransactionFee='" + sellTransactionFee + "'" +
                ", buyConsideration='" + buyConsideration + "'" +
                ", sellConsideration='" + sellConsideration + "'" +
                ", profit='" + profit + "'" +
                ", currency='" + currency + "'" +
                ", market='" + market + "'" +
                '}';
    }
}
