package com.samax.tech.webs.tag;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.samax.tech.webs.product.Product;

@Entity
@Table(name = "TAG")
public class Tag implements Serializable {
	
	private static final long serialVersionUID = 7765L;

	@Id
	@SequenceGenerator(name = "tag_generator", allocationSize = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_generator")
	private Long id;
	
	@Column(name = "TAG_NAME")
	private String name;
	
	@ManyToMany(mappedBy = "tags")
	@JsonBackReference
	private Set<Product> products = new HashSet<>();
	
	public Tag() {}
	
	public Tag(String name)
	{
		this.name = name;
	}
	
	public Tag(String name, Set<Product> products) {
		this.name = name;
		this.products = products;
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
	
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
