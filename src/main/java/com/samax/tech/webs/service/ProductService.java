package com.samax.tech.webs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samax.tech.webs.entity.Product;
import com.samax.tech.webs.repos.ProductRepository;

@Service
public class ProductService 
{
	private final ProductRepository repository;

	@Autowired
	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}
	
	public List<Product> getAllProduct()
	{
		return repository.findAll();
	}
	
	public List<Product> getProductsWithTag(String[] tags)
	{
		List<Product> all = getAllProduct();
		List<Product> result = new ArrayList<>();
		
		all.forEach(p -> {
			for(String tag : tags)
				for(String pTag : p.getTags())
					if(tag.equals(pTag))
					{
						result.add(p);
						return;
					}
		});
		
		return result;
	}

	public void putProduct(Product product) {
		if(observeProduct(product))
			repository.save(product);
		else
			System.out.println("The given product is invalid!");
	}
	
	private boolean observeProduct(Product product)
	{
		try {
			return !(product == null || product.getPopularity() < 0 ||
					product.getName().isBlank() || product.getName() == null ||
					product.getPrice() == null || product.getPriceRule() == null
					);
		} catch (Exception e) {
			return false;
		}
	}
	
}
