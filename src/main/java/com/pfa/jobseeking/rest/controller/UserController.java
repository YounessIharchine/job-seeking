package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.UserService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class UserController {

	@Autowired
	UserService userService;
	
	
	//Only ADMINs can list all users
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/users")
	List<User> listUsers() throws NotFoundException {
			
		return userService.findAll();
		
	}
	
	
	//The authenticated user can only see his information using email parameter
	@PreAuthorize("#email == authentication.name")
	@GetMapping(value = "/users", params = "email")
	User showUserByEmail(@RequestParam String email) throws NotFoundException {
		
		return userService.findUserByEmail(email);
		
	}
	
	@PostAuthorize("returnObject.email == authentication.name")
	@GetMapping("/users/{id}")
	User showUser(@PathVariable int id) throws NotFoundException {
		
		return userService.findById(id);
		
	}
	
	

}
