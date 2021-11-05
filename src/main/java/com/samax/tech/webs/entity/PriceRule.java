package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

public class PriceRule implements Serializable 
{
	private static final long serialVersionUID = 5671L;
	
	protected double priceReductionAmount;
	protected boolean active;
	
	public PriceRule()
	{
		priceReductionAmount = 0.0;
		active = false;
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
		return price.multiply(new BigDecimal(priceReductionAmount / 100.0));
	}
}
