package com.greenation.bigstock.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.greenation.bigstock.domain.util.CustomLocalDateSerializer;
import com.greenation.bigstock.domain.util.ISO8601LocalDateDeserializer;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Record
public abstract class TradingProfit extends BaseEntity{
	
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @NotNull
    @Column(name = "code", nullable = false)
    @Size(min=5, max=6)
    @Field(at=0)
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    @Field(at=1)
    private String name;

    @NotNull
    @Column(name = "quantity", precision=10, scale=2, nullable = false)
    @Field(at=2)
    private BigDecimal quantity;

    @NotNull
    @Column(name = "buy_price", precision=10, scale=2, nullable = false)
    @Field(at=3)
    private BigDecimal buyPrice;

    @NotNull
    @Column(name = "sell_price", precision=10, scale=2, nullable = false)
    @Field(at=4)
    private BigDecimal sellPrice;

    @NotNull
    @Column(name = "buy_transaction_fee", precision=10, scale=2, nullable = false)
    @Field(at=5)
    private BigDecimal buyTransactionFee;

    @NotNull
    @Column(name = "sell_transaction_fee", precision=10, scale=2, nullable = false)
    @Field(at=6)
    private BigDecimal sellTransactionFee;

    @Column(name = "buy_consideration", precision=10, scale=2, nullable = false)
    @Field(at=7)
    private BigDecimal buyConsideration;

    @Column(name = "sell_consideration", precision=10, scale=2, nullable = false)
    @Field(at=8)
    private BigDecimal sellConsideration;

    @NotNull
    @Column(name = "currency", nullable = false, length=3)
    @Field(at=9)
    private String currency;
    
    @NotNull
    @Column(name = "profit", precision=10, scale=2, nullable = false)
    @Field(at=10)
    private BigDecimal profit;

    @Column(name = "user_name", nullable = true)
    private String username;

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
        this.profit = calculateProfit();
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
    
    public BigDecimal calculateProfit(){
    	BigDecimal profit = sellPrice.subtract(buyPrice).multiply(quantity).subtract(buyTransactionFee).subtract(sellTransactionFee);
    	return profit;
    }
    
    public static List<String> getCSVHeader(){
    	List<String> header = new ArrayList<String>();
    	header.add("code");
    	header.add("name");
    	header.add("quantity");
    	header.add("buy price");
    	header.add("sell price");
    	header.add("buy transaction fee");
    	header.add("sell transaction fee");
    	header.add("buy consideration");
    	header.add("sell consideration");
    	header.add("currency");
    	header.add("profit");
    	return header;
    }
}
