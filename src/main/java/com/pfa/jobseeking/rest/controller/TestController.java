package com.pfa.jobseeking.rest.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.FillService;

@RestController
public class TestController {

	@Autowired
	FillService fillService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	@PostMapping("/api/images")
	void createImage(@RequestBody String image) throws IOException {
		byte[] imageBytes = Base64.getDecoder().decode(image);
		FileUtils.writeByteArrayToFile(new File(path+"\\image-test.jpeg"), imageBytes);
	}
	
	
	
	@GetMapping("/api/images")
	String showImage() throws IOException {
		byte[] imageBytes = FileUtils.readFileToByteArray(new File(path+"\\image-test.jpeg"));
		return Base64.getEncoder().encodeToString(imageBytes);
	}
	
	
	@GetMapping("/test")
	String test() {
		
		return "test";
		
	}
	
	
	
	@GetMapping("/authenticated")
	User showAuthenticatedUser(Principal principal) throws NotFoundException {
		
		User user = userRepository.findUserByEmail(principal.getName());
		return user;
		
	}
	
	
	
	@GetMapping("/fill")
	String fillDatabase() throws AlreadyExistsException, NotFoundException {
		
		fillService.fill();
		
		return "Database Filled";
	}
	
}
