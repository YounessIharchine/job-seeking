package com.pfa.jobseeking.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.dto.CompanyDto;
import com.pfa.jobseeking.rest.dto.PasswordChangeDto;
import com.pfa.jobseeking.rest.dto.UserDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.DoesNotMatchException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CodeResponse;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.rest.response.UserResponse;
import com.pfa.jobseeking.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
	//**********************************REGISTRATION**********************************
	@PostMapping("${rest.api.basePath}/users/registerSeeker")
	ResponseEntity<Response> registerSeeker(@RequestBody UserDto seekerDto) throws AlreadyExistsException {
		userService.saveSeeker(seekerDto);
		
		Response response = new Response(HttpStatus.OK.value(), "Registration Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("${rest.api.basePath}/users/registerCompany")
	ResponseEntity<Response> registerCompany(@RequestBody CompanyDto companyDto) throws AlreadyExistsException, IOException {
		userService.saveCompany(companyDto);
		
		Response response = new Response(HttpStatus.OK.value(), "Registration Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	
	//**********************************AUTHENTICATED USER INFO**********************************
	@GetMapping("${rest.api.basePath}/users/getAuthenticatedUserInfo")
	UserResponse getAuthenticatedUserInfo() throws AccessDeniedException {
		return userService.getAuthenticatedUserInfo();
	}
	
	
	
	//**********************************PASSWORD CHANGE**********************************
	@GetMapping("${rest.api.basePath}/users/checkInfo")
	void checkInfo(@RequestParam String email, @RequestParam(required = false) String code) throws NotFoundException, DoesNotMatchException {
		userService.checkInfo(email, code);
	}
	
	@GetMapping("${rest.api.basePath}/users/changePassword")
	CodeResponse generateCode(@RequestParam String email) throws NotFoundException {
		return userService.generateCode(email);
	}
	
	@PostMapping("${rest.api.basePath}/users/changePassword")
	void changePassword(@RequestBody PasswordChangeDto passwordChangeDto) throws DoesNotMatchException, NotFoundException {
		userService.changePassword(passwordChangeDto);
	}
}








//Only ADMINs can list all users
//@PreAuthorize("hasRole('ROLE_ADMIN')")
//@GetMapping("/users")
//List<User> listUsers(@RequestParam(required = false) Integer page, 
//		@RequestParam(required = false, defaultValue = "20") Integer limit, 
//		@RequestParam(required = false) String[] sort) 
//				throws NotFoundException {
//	
//	if(page == null)
//		return userService.findAll();
//	else if(sort == null)
//		return userService.findAll(PageRequest.of(page, limit));
//	
//	List<Order> orders = new ArrayList<>();
//	for(String field : sort) {
//		
//		if(field.contains("desc"))
//			orders.add(Sort.Order.desc(field.substring(0, field.length()-4)));
//		else if(field.contains("asc"))
//			orders.add(Sort.Order.asc(field.substring(0, field.length()-3)));
//		else
//			orders.add(Sort.Order.asc(field));
//	}
//	
//	return userService.findAll(PageRequest.of(page, limit, Sort.by(orders)));
//	
//}
//
//
//
////The authenticated user can only see his information using email parameter
//@PreAuthorize("#email == authentication.name")
//@GetMapping(value = "/users", params = "email")
//User showUserByEmail(@RequestParam String email) throws NotFoundException {
//	
//	return userService.findUserByEmail(email);
//	
//}
//
//@PostAuthorize("returnObject.email == authentication.name")
//@GetMapping("/users/{id}")
//User showUser(@PathVariable int id) throws NotFoundException {
//	
//	return userService.findById(id);
//	
//}
