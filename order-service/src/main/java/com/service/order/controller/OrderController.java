package com.service.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.order.dto.OrderRequest;
import com.service.order.service.OrderService;


@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/place_order", method=RequestMethod.POST)
	@CircuitBreaker(name="inventory", fallbackMethod="fallbackMethod")
	public ResponseEntity<OrderRequest> placeOrder(@RequestBody OrderRequest orderRequest) {
		System.out.println("Placing new order ...");
		orderRequest = orderService.placeOrder(orderRequest);
		
		return new ResponseEntity<OrderRequest>(orderRequest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<OrderRequest> getProductList() {
		List<OrderRequest> orders = orderService.getOrders();
		
		return orders;
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<OrderRequest> myOrders(@RequestParam("user_id") Long userID) {
		List<OrderRequest> orders = orderService.getUserOrders(userID);
		
		return orders;
	}
	
	public ResponseEntity<OrderRequest> fallbackMethod(Throwable throwable){
		OrderRequest orderRequest = null;
		System.out.println("Executing fallback method ..");
		return new ResponseEntity<OrderRequest>(orderRequest, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
}

