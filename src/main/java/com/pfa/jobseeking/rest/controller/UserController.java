package com.pfa.jobseeking.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
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
	List<User> listUsers(@RequestParam(required = false) Integer page, 
			@RequestParam(required = false, defaultValue = "20") Integer limit, 
			@RequestParam(required = false) String[] sort,
			@RequestParam(required = false) String[] direction) 
					throws NotFoundException {
		
		if(page == null)
			return userService.findAll();
		else if(sort == null)
			return userService.findAll(PageRequest.of(page, limit));
		
		List<Order> orders = new ArrayList<>();
		for(String field : sort) {
			
			if(field.contains("desc"))
				orders.add(Sort.Order.desc(field.substring(0, field.length()-4)));
			else if(field.contains("asc"))
				orders.add(Sort.Order.asc(field.substring(0, field.length()-3)));
			else
				orders.add(Sort.Order.asc(field));
		}
		
		return userService.findAll(PageRequest.of(page, limit, Sort.by(orders)));
		
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
