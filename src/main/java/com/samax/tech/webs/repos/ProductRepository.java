package com.samax.tech.webs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samax.tech.webs.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
