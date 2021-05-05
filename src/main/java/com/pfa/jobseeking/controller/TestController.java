package com.pfa.jobseeking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.entity.User;
import com.pfa.jobseeking.repository.UserRepository;

@RestController
public class TestController {

	@Autowired
	UserRepository userService;
	
	@GetMapping("/test")
	String test() {
		return "test";
	}
	
	@GetMapping("/users")
	List<User> getUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/authenticated")
	User showAuthenticatedUser(Principal principal) {
		User user = userService.findUserByUsername(principal.getName());
		return user;
	}
	
}
