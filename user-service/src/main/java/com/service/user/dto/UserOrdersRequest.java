package com.service.user.dto;

import java.util.List;

public class UserOrdersRequest {
	
	private List<OrderRequest> orders;
	
	public List<OrderRequest> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderRequest> orders) {
		this.orders = orders;
	}
}
