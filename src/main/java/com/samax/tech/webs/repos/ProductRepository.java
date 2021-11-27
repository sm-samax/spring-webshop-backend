package com.samax.tech.webs.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.samax.tech.webs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("FROM Product p INNER JOIN p.tags as t WHERE t.name IN (:tags)")
	public List<Product> findByTagNameIn(@Param("tags") String[] tags);
	
	public List<Product> findByNameContainingIgnoreCase(String name);
}
