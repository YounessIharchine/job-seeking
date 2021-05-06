package com.pfa.jobseeking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	String test() {
		
		return "test";
		
	}
	
	@GetMapping("/users")
	List<User> getUsers() {
		
		List<User> users = userService.findAll();
		return users;
		
	}
	
	@GetMapping("/authenticated")
	User showAuthenticatedUser(Principal principal) {
		
		User user = userService.findUserByEmail(principal.getName());
		return user;
		
	}
	
	@GetMapping("/fill")
	String fillDatabase() {
		
		Role seekerRole = new Role("ROLE_SEEKER");
		Role companyRole = new Role("ROLE_COMPANY");
		Role adminRole = new Role("ROLE_ADMIN");
		
		
		Seeker seeker = new Seeker("seeker@gmail.com", "seeker", "first", "last");
		seeker.addRole(seekerRole);
		Company company = new Company("company@gmail.com", "company");
		company.addRole(companyRole);
		Admin admin = new Admin("admin@gmail.com", "admin");
		admin.addRole(adminRole);
		
		
		
		userService.save(seeker);
		userService.save(company);
		userService.save(admin);
		
		return "Database Filled";
	}
	
}
