package com.service.order.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.order.client.InventoryClient;
import com.service.order.dto.OrderRequest;
import com.service.order.model.Order;
import com.service.order.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private InventoryClient inventoryClient;
	
	public OrderRequest placeOrder(OrderRequest orderRequest) {
		Order order = mapOrderRequestToOrder(orderRequest);
		Integer availableQuantity = inventoryClient.getProductQuantity(orderRequest.getProductId());
		
		if(orderRequest.getQuantity() > availableQuantity) {
			System.out.println("Requested Quantity: " + orderRequest.getQuantity() + "\t Availlable Quantity: " + availableQuantity);
			throw new RuntimeException("Insuffient quantity");
		}
		
		order = orderRepository.save(order);
		
		orderRequest = mapOrderToOrderRequest(order);
		return orderRequest;
	}
	
	public List<OrderRequest> getUserOrders(Long userID) {
		List<Order> orders = orderRepository.findByUserId(userID);
		List<OrderRequest> orderRequests = mapOrdersListToOrderRequestsList(orders);
		
		return orderRequests;
	}
	
	public List<OrderRequest> getOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderRequest> orderRequests = mapOrdersListToOrderRequestsList(orders);
		
		return orderRequests;
	}
	
	// Map OrderRequest to Order
	private Order mapOrderRequestToOrder(OrderRequest orderRequest) {
		Order order = new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setUserId(orderRequest.getUserId());
		order.setProductId(orderRequest.getProductId());
		order.setQuantity(orderRequest.getQuantity());
		order.setPrice(orderRequest.getPrice());
		
		return order;
	}
	
	// Map Order to OrderRequest
	private OrderRequest mapOrderToOrderRequest(Order order) {
		OrderRequest orderRequest = new OrderRequest();
		
		orderRequest.setId(order.getId());
		orderRequest.setOrderNumber(order.getOrderNumber());
		orderRequest.setUserId(order.getUserId());
		orderRequest.setProductId(order.getProductId());
		orderRequest.setPrice(order.getPrice());
		orderRequest.setQuantity(order.getQuantity());
		
		return orderRequest;
	}
	
	// Map OrdersList to OrderRequestsList
	private List<OrderRequest> mapOrdersListToOrderRequestsList(List<Order> orders) {
		
		return orders.stream()
				.map(this::mapOrderToOrderRequest)
				.collect(Collectors.toList());
	}

}
