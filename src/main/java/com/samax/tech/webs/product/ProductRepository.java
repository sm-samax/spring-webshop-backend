package com.samax.tech.webs.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("FROM Product p INNER JOIN p.tags as t WHERE t.name IN (:tags)")
	public List<Product> findByTagNameIn(@Param("tags") String[] tags);
	
	public List<Product> findByNameContainingIgnoreCase(String name);
	
	public long deleteProductByName(String name);
	
	public long deleteProductById(Long id);
}
