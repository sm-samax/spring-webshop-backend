package com.samax.tech.webs.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Product_Purchase")
public class ProductPurchase implements Serializable{

	private static final long serialVersionUID = 19161L;

	@Id
	@SequenceGenerator(name = "product_purchase_gen")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_purchase_gen")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JsonManagedReference
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	@JsonManagedReference
	private Purchase purchase;
	
	@Column(nullable = false)
	private int quantity;
	
	public ProductPurchase() {}
	
	public ProductPurchase(Product product, Purchase purchase, int quantity) {
		this.product = product;
		this.purchase = purchase;
		this.quantity = quantity;
		
		this.product.getPurchases().add(this);
		this.purchase.getProducts().add(this);
		this.purchase.updateSum(this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
