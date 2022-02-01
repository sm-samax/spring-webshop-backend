package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Purchase implements Serializable {

	private static final long serialVersionUID = 5566L;

	@Id
	@SequenceGenerator(name = "purchase_gen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_gen")
	private Long id;

	@Embedded
	private Client client;

	@Column(nullable = false)
	private BigDecimal sum;

	@Column(nullable = false)
	private Currency currency;

	@OneToMany(mappedBy = "purchase", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JsonBackReference
	private List<ProductPurchase> products = new ArrayList<>();
	
	public Purchase() {
	}
	
	public Purchase(Client client, Currency currency) {
		this.client = client;
		this.currency = currency;
		this.sum = new BigDecimal(0.0);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void updateSum(ProductPurchase productPurchase) {
		BigDecimal big = new BigDecimal(
				productPurchase.getProduct().getCurrentPrice().doubleValue() * productPurchase.getQuantity());
		sum = sum.add(big);
	}

	public List<ProductPurchase> getProducts() {
		return products;
	}

	public void setProducts(List<ProductPurchase> products) {
		this.products = products;
	}
}
