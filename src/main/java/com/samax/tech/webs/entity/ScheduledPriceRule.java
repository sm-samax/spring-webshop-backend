package com.samax.tech.webs.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ScheduledPriceRule extends PriceRule {

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public ScheduledPriceRule(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public boolean isActive()
	{
		LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
		return active && startTime.isBefore(now) && endTime.isAfter(now); 
	}
}
