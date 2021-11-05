package com.samax.tech.webs.entity;

import java.math.BigDecimal;

public final class NoPriceRule extends PriceRule {
	
	@Override
	public boolean isActive() {
		return false;
	}
	
	@Override
	public BigDecimal apply(BigDecimal price) {
		return price;
	}
}
