package com.service.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	

}
