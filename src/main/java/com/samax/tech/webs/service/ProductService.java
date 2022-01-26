package com.samax.tech.webs.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import com.samax.tech.webs.entity.Product;
import com.samax.tech.webs.repos.ProductRepository;

@Service
public class ProductService 
{
	private final ProductRepository repository;
	private final SessionFactory factory;
	
	@Autowired
	public ProductService(ProductRepository repository, SessionFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}
	
	public List<Product> getAllProduct()
	{
		return repository.findAll();
	}
	
	public Optional<Product> getProductById(Long id)
	{
		return Optional.of(repository.getById(id));
	}
	
	public List<Product> getProductsWithTag(String[] tags)
	{
		return repository.findByTagNameIn(tags);
	}
	
	public List<Product> getProductsByNameContainingIgnoreCase(String name)
	{
		return repository.findByNameContainingIgnoreCase(name);
	}

	public void postProduct(Product product) {
		if(observeProduct(product))
			save(product);
		else
			System.out.println("The given product is invalid!");
	}
	
	
	public long deleteProductByName(String name)
	{
		return repository.deleteProductByName(name);
	}
	
	public long deleteProductById(Long id)
	{
		return repository.deleteProductById(id);
	}
	
	private void save(Product product)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			session.save(product);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null) tx.rollback();
		}
		finally {
			session.close();
		}
	}
	
	private boolean observeProduct(Product product)
	{
		try {
			return !(product == null || product.getName() == null || product.getName().isBlank() ||
					product.getPrice() == null);
		} catch (Exception e) {
			return false;
		}
	}
}
