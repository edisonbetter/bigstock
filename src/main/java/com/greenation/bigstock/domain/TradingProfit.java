package com.greenation.bigstock.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.greenation.bigstock.domain.util.CustomLocalDateSerializer;
import com.greenation.bigstock.domain.util.ISO8601LocalDateDeserializer;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class TradingProfit {
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

    

    @Column(name = "user_name", nullable = true)
    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }
}
