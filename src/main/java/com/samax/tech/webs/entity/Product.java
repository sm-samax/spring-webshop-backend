package com.samax.tech.webs.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
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
	
	@Column(nullable = false)
	private String[] tags;
	
	@Column(nullable = false)
	private BigDecimal price;
	
	private boolean priceRulePresent;
	private PriceRule priceRule;
	private boolean available;
	private int amountLeft;
	private int popularity;
	
	public Product() {
		
	}
	
	public Product(String name, String details, String thumbNailURL, String[] imagesURL, String[] tags,
			BigDecimal price, boolean priceRulePresent, PriceRule priceRule, boolean available, int amountLeft,
			int popularity) {
		setName(name);
		setDetails(details);
		setThumbnailURL(thumbNailURL);
		setImagesURL(imagesURL);
		setTags(tags);
		setPrice(price);
		setPriceRulePresent(priceRulePresent);
		setPriceRule(priceRule);
		setAmountLeft(amountLeft);
		setAvailable(available);
		setPopularity(popularity);
	}
	
	public Product(String name, String details, String thumbNailURL, String[] imagesURL, String[] tags,
			BigDecimal price, boolean available, int amountLeft, int popularity) {
		setName(name);
		setDetails(details);
		setThumbnailURL(thumbNailURL);
		setImagesURL(imagesURL);
		setTags(tags);
		setPrice(price);
		setPriceRulePresent(false);
		setPriceRule(noPriceRule);
		setAmountLeft(amountLeft);
		setAvailable(available);
		setPopularity(popularity);
	}
	
	public Product(String name, String details, String thumbNailURL, String[] imagesURL, String[] tags,
			BigDecimal price, boolean priceRulePresent, PriceRule priceRule, boolean available, int amountLeft) {
		setName(name);
		setDetails(details);
		setThumbnailURL(thumbNailURL);
		setImagesURL(imagesURL);
		setTags(tags);
		setPrice(price);
		setPriceRulePresent(priceRulePresent);
		setPriceRule(priceRule);
		setAmountLeft(amountLeft);
		setAvailable(available);
		setPopularity(defaultPopularity);
	}
	
	public Product(String name, String details, String thumbNailURL, String[] imagesURL, String[] tags,
			BigDecimal price, boolean available, int amountLeft) {
		setName(name);
		setDetails(details);
		setThumbnailURL(thumbNailURL);
		setImagesURL(imagesURL);
		setTags(tags);
		setPrice(price);
		setPriceRulePresent(false);
		setPriceRule(noPriceRule);
		setAmountLeft(amountLeft);
		setAvailable(available);
		setPopularity(defaultPopularity);
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

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
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
		if(isPriceRulePresent())
			this.priceRule = priceRule;
		else
			this.priceRule = noPriceRule;
	}

	public boolean isPriceRulePresent() {
		return priceRulePresent;
	}

	public void setPriceRulePresent(boolean priceRulePresent) {
		this.priceRulePresent = priceRulePresent;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		if(getAmountLeft() > 0)
			this.available = available;
		else
			this.available = false;
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

	@Override
	public int compareTo(Product p) {
		return defaultComparator.compare(this, p);
	}

}
