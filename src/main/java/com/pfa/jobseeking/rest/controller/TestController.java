package com.pfa.jobseeking.rest.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	
	@GetMapping(
			value = "/api/get-image-with-media-type", 
			produces = MediaType.APPLICATION_PDF_VALUE
			)
	public @ResponseBody byte[] getImageWithMediaType() throws IOException {
		byte[] imageBytes = FileUtils.readFileToByteArray(new File(path+"\\documents\\document-6.pdf"));
	    return imageBytes;
	}
	
	@GetMapping("/api/image")
	public ResponseEntity<Resource> download(@RequestParam("image") String image) throws IOException {
        File file = new File(path + File.separator + image + ".png");

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.png");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
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
	String fillDatabase() throws AlreadyExistsException, NotFoundException, IOException {
		
		fillService.fill();
		
		return "Database Filled";
	}
	
}
