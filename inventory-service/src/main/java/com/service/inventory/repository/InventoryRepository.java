package com.service.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.service.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	
	@Query(value = "SELECT i.quantity "
			+ "FROM Inventory i "
			+ "WHERE i.productId = :productID")
	public Integer findQuantityByProduct(Long productID);

}
