package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PRICERULE")
public class PriceRule implements Serializable 
{
	private static final long serialVersionUID = 5671L;
	
	@Transient
	private BigDecimal reductor;
	
	@Id
	@SequenceGenerator(name = "pricerule_generator", initialValue = 11, allocationSize = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pricerule_generator")
	private Long id;
	
	private double priceReductionAmount;
	private boolean active;
	
	@OneToMany(mappedBy = "priceRule")
	@JsonBackReference
	private Set<Product> products;
	
	public PriceRule()
	{
		products = new HashSet<>();
	}
	
	public PriceRule(double priceReductionAmount, boolean active) {
		this();
		this.priceReductionAmount = priceReductionAmount;
		this.active = active;
		reductor = new BigDecimal(priceReductionAmount / 100.0);
	}
	
	
	public PriceRule(double priceReductionAmount, boolean active, Set<Product> products) {
		this.priceReductionAmount = priceReductionAmount;
		this.active = active;
		this.products = products;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Product> getProducts() {
		return products;
	}
	
	public void setProducts(Set<Product> products) {
		this.products = products;
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
	
	public boolean isActive() {
		return active;
	}
	
	public BigDecimal apply(BigDecimal price)
	{
		if(reductor == null)
			reductor = new BigDecimal(priceReductionAmount / 100.0);
		
		return price.multiply(reductor);
	}
}
