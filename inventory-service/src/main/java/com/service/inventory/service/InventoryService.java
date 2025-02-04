package com.service.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.inventory.dto.InventoryRequest;
import com.service.inventory.model.Inventory;
import com.service.inventory.repository.InventoryRepository;

@Service
public class InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRespository;
	
	public Integer checkQuantity(Long productID) {
		Integer existinQquantity = inventoryRespository.findQuantityByProduct(productID);
		
		return existinQquantity;
	}
	
	public InventoryRequest addInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = mapInventoryRequestToInventory(inventoryRequest);
		inventory = inventoryRespository.save(inventory);
		
		inventoryRequest = mapInventoryToInventoryRequest(inventory);
		return inventoryRequest;
	}
	
	public InventoryRequest updateInventory(Long id, InventoryRequest inventoryRequest) {
		Inventory inventory = inventoryRespository.findById(id)
				.orElseThrow(() -> new RuntimeException("Item Not found"));
		
		inventory.setProductId(inventoryRequest.getProductId());
		inventory.setQuantity(inventoryRequest.getQuantity());
		
		inventory = inventoryRespository.save(inventory);
		
		inventoryRequest = mapInventoryToInventoryRequest(inventory);
		return inventoryRequest;
	}
	
	public List<InventoryRequest> getAllInventoryItems() {
		List<Inventory> inventories = inventoryRespository.findAll();
		
		return mapInventoryListToInventoryRequestsList(inventories);
	}
	
	// map InventoryRequest to Inventory
	private Inventory mapInventoryRequestToInventory(InventoryRequest inventoryRequest) {
		Inventory inventory = new Inventory();
		
		inventory.setProductId(inventoryRequest.getProductId());
		inventory.setQuantity(inventoryRequest.getQuantity());
		
		return inventory;
	}
	
	// map Inventory to InventoryRequest
	private InventoryRequest  mapInventoryToInventoryRequest(Inventory inventory) {
		InventoryRequest inventoryRequest = new InventoryRequest();
		
		inventoryRequest.setId(inventory.getId());
		inventoryRequest.setProductId(inventory.getProductId());
		inventoryRequest.setQuantity(inventory.getQuantity());
		
		return inventoryRequest;
	}
	
	// map InventoryList to InventoryRequestsList
	private List<InventoryRequest> mapInventoryListToInventoryRequestsList(List<Inventory> inventoryList) {
		
		return inventoryList.stream()
				.map(this::mapInventoryToInventoryRequest)
				.collect(Collectors.toList());
	}

}
