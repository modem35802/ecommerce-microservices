package com.service.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.user.client.OrderClient;
import com.service.user.dto.OrderRequest;
import com.service.user.dto.UserOrdersRequest;
import com.service.user.dto.UserRequest;
import com.service.user.model.User;
import com.service.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private OrderClient orderClient;
	
	@Autowired
	private UserRepository userRepository;
	
	public UserRequest createUser(UserRequest userRequest) {
		User user = mapUserRequestToUser(userRequest);
		
		User existingUser = userRepository.findByEmailOrMobileNumber(user.getEmail(), user.getMobileNumber());
		
		if(existingUser != null) {
			throw new RuntimeException("User is already existing");
		}
		
		userRepository.save(user);
		
		userRequest = mapUserToUserRequest(user);
		
		return userRequest;
	}
	
	public UserRequest updateUser(Long userID, UserRequest userRequest) {
		
		User existingUser = userRepository.findById(userID)
				.orElseThrow(() -> new RuntimeException("User does not exist"));
		
		// update
		existingUser.setFirstName(userRequest.getFirstName());
		existingUser.setLastName(userRequest.getLastName());
		existingUser.setGender(userRequest.getGender());
		existingUser.setEmail(userRequest.getEmail());
		existingUser.setMobileNumber(userRequest.getMobileNumber());
		
		userRepository.save(existingUser);
		
		userRequest = mapUserToUserRequest(existingUser);
	
		return userRequest;
	}
	
	public List<UserRequest> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserRequest> usersList = mapUserListToUserRequestsList(users);
		
		return usersList;
	}
	
	
	public UserOrdersRequest getUserOrders(Long userID) {
		User existingUser = userRepository.findById(userID)
				.orElseThrow(() -> new RuntimeException("User does not exist"));
		
		UserOrdersRequest userOrders = new UserOrdersRequest();
		
		List<OrderRequest> orders = orderClient.getUserOrders(existingUser.getId());
		userOrders.setOrders(orders);
		
		return userOrders;
	}
	
	// map userRequest to user
	private User mapUserRequestToUser(UserRequest userRequest) {
		User user = new User();
		
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setGender(userRequest.getGender().toUpperCase());
		user.setEmail(userRequest.getEmail());
		user.setMobileNumber(userRequest.getMobileNumber());
		
		return user;
	}
	
	// map user to userRequest
	private UserRequest mapUserToUserRequest(User user) {
		UserRequest userRequest = new UserRequest();
		
		userRequest.setId(user.getId());
		userRequest.setFirstName(user.getFirstName());
		userRequest.setLastName(user.getLastName());
		userRequest.setGender(user.getGender().toUpperCase());
		userRequest.setEmail(user.getEmail());
		userRequest.setMobileNumber(user.getMobileNumber());
			
		return userRequest;
	}
	
	// map usetlist to userrequestlist
	private List<UserRequest> mapUserListToUserRequestsList(List<User> users) {
		
		return users.stream()
				.map(this::mapUserToUserRequest)
				.collect(Collectors.toList());	
	}
}
