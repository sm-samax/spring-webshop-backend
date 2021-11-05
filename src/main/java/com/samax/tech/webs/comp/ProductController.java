package com.samax.tech.webs.comp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samax.tech.webs.entity.Product;
import com.samax.tech.webs.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController 
{
	private final ProductService service;
	
	@Autowired
	public ProductController(ProductService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProduct()
	{
		return new ResponseEntity<List<Product>>(service.getAllProduct(), HttpStatus.OK);
	}
	
	@GetMapping("/{tags}")
	public ResponseEntity<List<Product>> getProductsWithTag(@PathVariable String[] tags)
	{
		return new ResponseEntity<List<Product>>(service.getProductsWithTag(tags), HttpStatus.OK);
	}
	
	@PutMapping
	public void putProduct(RequestEntity<Product> product)
	{
		service.putProduct(product.getBody());
	}
}
