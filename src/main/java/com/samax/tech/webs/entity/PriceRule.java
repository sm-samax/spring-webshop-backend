package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.persistence.Inheritance;
import javax.persistence.Transient;

public class PriceRule implements Serializable 
{
	private static final long serialVersionUID = 5671L;
	
	@Transient
	private BigDecimal reductor;
	
	private double priceReductionAmount;
	private boolean active;
	
	public PriceRule()
	{
	}
	
	public PriceRule(double priceReductionAmount, boolean active) {
		this.priceReductionAmount = priceReductionAmount;
		this.active = active;
	}

	public double getPriceReductionAmount() {
		return priceReductionAmount;
	}

	public void setPriceReductionAmount(double priceReductionAmount) {
		this.priceReductionAmount = priceReductionAmount;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive()
	{
		return active;
	}
	
	public BigDecimal apply(BigDecimal price)
	{
		if(reductor == null)
			reductor = new BigDecimal(priceReductionAmount / 100.0);
		
		return price.multiply(reductor);
	}
}
