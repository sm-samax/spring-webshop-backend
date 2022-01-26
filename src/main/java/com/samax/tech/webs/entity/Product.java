package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product implements Serializable
{
	private static final long serialVersionUID = 1119L;
	
	@Id
	@SequenceGenerator(name = "productSequenceGenerator", initialValue = 137, allocationSize = 4)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequenceGenerator")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String details;
	private String imageURL;
	private int amountLeft;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Tag> tags;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private PriceRule priceRule;
	
	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<ProductToPurchase> toPurchase;
	
	public Product() {
		this.toPurchase = new ArrayList<>();
	}
	
	public Product(String name, String details, String imageURL, Set<Tag> tags,
			BigDecimal price, PriceRule priceRule, int amountLeft) {
		this.toPurchase = new ArrayList<>();
		setName(name);
		setDetails(details);
		setImageURL(imageURL);
		setTags(tags);
		setPrice(price);
		setPriceRule(priceRule);
		setAmountLeft(amountLeft);
	}
	
	public Product(String name, String details, String imageURL, Set<Tag> tags,
			BigDecimal price, int amountLeft) 
	{
		this(name, details, imageURL, tags, price, null, amountLeft);
	}

	public Product(String name, String details, Set<Tag> tags, BigDecimal price, int amountLeft) {
		this(name, details, null, tags, price, null, amountLeft);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
		for(Tag tag : tags)
			tag.getProducts().add(this);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price.doubleValue() > 0.0 ? price : BigDecimal.ZERO;
	}

	public PriceRule getPriceRule() {
		return priceRule;
	}

	public void setPriceRule(PriceRule priceRule) {
			this.priceRule = priceRule;
			if(priceRule != null)
			priceRule.getProducts().add(this);
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft > 0 ? amountLeft : 0;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public List<ProductToPurchase> getToPurchase() {
		return toPurchase;
	}

	public void setToPurchase(List<ProductToPurchase> toPurchase) {
		this.toPurchase = toPurchase;
	}

	public BigDecimal getCurrentPrice()
	{
		if(priceRule != null)
			return priceRule.apply(price);
		else
			return price;
	}

	public boolean isAvailable() {
		return getAmountLeft() > 0;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && this.getClass() == obj.getClass() && this.id == ((Product)obj).id)
			return true;
		
		return false;
	}
}
