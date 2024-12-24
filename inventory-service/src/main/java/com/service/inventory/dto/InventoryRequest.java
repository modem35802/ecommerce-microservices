package com.service.inventory.dto;

public class InventoryRequest {
	
	private Long id;
	private String productId;
	private Integer quantity;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InventoryRequest [id=" + id
				+ ", quantity=" + quantity 
				+ "]";
	}
}
