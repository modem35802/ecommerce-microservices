package com.service.user.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.service.user.dto.OrderRequest;

@FeignClient(value = "order", url = "http://localhost:8081")
public interface OrderClient {
	
	@RequestMapping(method = RequestMethod.GET, value = "/api/order/user")
	public List<OrderRequest> getUserOrders(@RequestParam("user_id") Long userID);

}

