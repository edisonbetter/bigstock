package com.greenation.bigstock.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="Total_Profit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TotalProfit extends BaseEntity{
	@Column(name="user_name", nullable=false)
	private String username;
	
	@Column(name="cny_profits", nullable=false)
	private BigDecimal cnyProfits;
	
	@Column(name="hkd_profits", nullable=false)
	private BigDecimal hkdProfits;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getCnyProfits() {
		return cnyProfits;
	}

	public void setCnyProfits(BigDecimal cnyProfits) {
		this.cnyProfits = cnyProfits;
	}

	public BigDecimal getHkdProfits() {
		return hkdProfits;
	}

	public void setHkdProfits(BigDecimal hkdProfits) {
		this.hkdProfits = hkdProfits;
	}
	
	
}
