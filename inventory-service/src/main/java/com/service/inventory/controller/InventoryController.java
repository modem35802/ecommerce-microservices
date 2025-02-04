package com.service.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.inventory.dto.InventoryRequest;
import com.service.inventory.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@RequestMapping(value = "/quantity", method = RequestMethod.GET)
	public Integer getProductQuantity(@RequestParam("product_id") Long productID) {
		
		return inventoryService.checkQuantity(productID);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<InventoryRequest> addInventory(@RequestBody InventoryRequest inventoryRequest) {
		inventoryRequest = inventoryService.addInventory(inventoryRequest);
		
		return new ResponseEntity<InventoryRequest>(inventoryRequest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<InventoryRequest> updateInventory(@RequestParam("id") Long id,
			@RequestBody InventoryRequest inventoryRequest) {
		inventoryRequest = inventoryService.updateInventory(id, inventoryRequest);
		
		return new ResponseEntity<InventoryRequest>(inventoryRequest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryRequest>> getAllInventoryItems() {
		List<InventoryRequest> inventories = inventoryService.getAllInventoryItems();
		
		return new ResponseEntity<List<InventoryRequest>>(inventories, HttpStatus.OK);
	}
	
}
