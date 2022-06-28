package com.samax.tech.webs.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(path = "api/products")
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
		return ResponseEntity.ok(service.getAllProduct());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id)
	{
		return ResponseEntity.ok(service.getProductById(id));
	}
	
	@GetMapping("/t={tags}")
	public ResponseEntity<List<Product>> getProductsWithTag(@PathVariable String[] tags)
	{
		return ResponseEntity.ok(service.getProductsWithTag(tags));
	}
	
	@GetMapping("/n={name}")
	public ResponseEntity<List<Product>> getProductsWithNameContainingIgnoreCase(@PathVariable String name)
	{
		return ResponseEntity.ok(service.getProductsByNameContainingIgnoreCase(name));
	}
	
	@PostMapping("/post")
	public void postProduct(@RequestBody Product product)
	{
		service.postProduct(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public long deleteProductById(@PathVariable Long id)
	{
		return service.deleteProductById(id);
	}
	
	@DeleteMapping("/delete/n={name}")
	public long deleteProductByName(@PathVariable String name)
	{
		return service.deleteProductByName(name);
	}
}
