package com.service.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.user.dto.UserOrdersRequest;
import com.service.user.dto.UserRequest;
import com.service.user.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<UserRequest> createUser(@RequestBody UserRequest userRequest) {
		
		userRequest = userService.createUser(userRequest);
		
		return new ResponseEntity<UserRequest>(userRequest, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResponseEntity<UserRequest> updateUser(@RequestParam("user_id") Long userID,
			@RequestBody UserRequest userRequest) {
		
		userRequest = userService.updateUser(userID, userRequest);
		
		return new ResponseEntity<UserRequest>(userRequest, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserRequest> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
	public ResponseEntity<UserOrdersRequest> myOrders(@RequestParam("user_id") Long userID) {
		
		UserOrdersRequest userOrders = userService.getUserOrders(userID);
		
		return new ResponseEntity<UserOrdersRequest>(userOrders, HttpStatus.OK);
	}

}

