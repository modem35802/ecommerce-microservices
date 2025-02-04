package com.service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.service.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailOrMobileNumber(String email, String mobileNumber);

}
