package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable, Comparable<Product>
{
	private static final long serialVersionUID = 1119L;
	private static final PriceRule noPriceRule = new NoPriceRule();
	private static final Comparator<Product> defaultComparator = Comparator.comparing(Product::getPopularity);
	private static final int defaultPopularity = 100;
	
	@Id
	@SequenceGenerator(name = "productSequenceGenerator", initialValue = 137, allocationSize = 4)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSequenceGenerator")
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String details;
	private String thumbnailURL;
	private String[] imagesURL;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Tag> tags;
	
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private PriceRule priceRule;
	private int amountLeft;
	private int popularity;
	
	public Product() {}
	
	public Product(String name, String details, String thumbNailURL, String[] imagesURL, Set<Tag> tags,
			BigDecimal price, PriceRule priceRule, int amountLeft, int popularity) {
		setName(name);
		setDetails(details);
		setThumbnailURL(thumbNailURL);
		setImagesURL(imagesURL);
		setTags(tags);
		setPrice(price);
		setPriceRule(priceRule);
		setAmountLeft(amountLeft);
		setPopularity(popularity);
	}
	
	public Product(String name, String details, String thumbnailURL, String[] imagesURL, Set<Tag> tags,
			BigDecimal price, int amountLeft, int popularity) 
	{
		this(name, details, thumbnailURL, imagesURL, tags, price, noPriceRule, amountLeft, popularity);
	}

	public Product(String name, String details, Set<Tag> tags, BigDecimal price, int amountLeft) {
		this(name, details, "nullURL", new String[]{}, tags, price, noPriceRule, amountLeft, defaultPopularity);
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

	public String getThumbnailURL() {
		return thumbnailURL;
	}

	public void setThumbnailURL(String thumbNailURL) {
		this.thumbnailURL = thumbNailURL;
	}

	public String[] getImagesURL() {
		return imagesURL;
	}

	public void setImagesURL(String[] imagesURL) {
		this.imagesURL = imagesURL;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
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
	}

	public int getAmountLeft() {
		return amountLeft;
	}

	public void setAmountLeft(int amountLeft) {
		this.amountLeft = amountLeft > 0 ? amountLeft : 0;
	}
	
	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}

	public BigDecimal getCurrentPrice()
	{
		if(priceRule.isActive())
			return priceRule.apply(price);
		else
			return price;
	}

	public boolean isPriceRulePresent() {
		return priceRule != null && priceRule != noPriceRule;
	}

	public boolean isAvailable() {
		return getAmountLeft() > 0;
	}
	
	@Override
	public int compareTo(Product p) {
		return defaultComparator.compare(this, p);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && this.getClass() == obj.getClass() && this.id == ((Product)obj).id)
			return true;
		
		return false;
	}
}
