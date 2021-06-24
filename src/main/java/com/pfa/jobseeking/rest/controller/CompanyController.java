package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.TextDto;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	@GetMapping("${rest.api.basePath}/companies/{id}")
	CompanyResponse getCompany(@PathVariable(name = "id") int id) {
		return companyService.findCompany(id);
	}
	

	@PatchMapping("${rest.api.basePath}/companies/profile")
	ResponseEntity<Response> updateCompanyInfo(@RequestBody Map<String, String> map) throws IOException {
		companyService.updateInfo(map);
		
		Response response = new Response(HttpStatus.OK.value(), "Update Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping("${rest.api.basePath}/companies/paragraphs")
	void addParagraph(@RequestBody TextDto textDto) {
		companyService.addParagraph(textDto);
	}
	
	@GetMapping("${rest.api.basePath}/companies/paragraphs")
	Set<Paragraph> findParagraphs() {
		return companyService.findParagraphs();
	}
	
	@DeleteMapping("${rest.api.basePath}/companies/paragraphs")
	void deleteParagraph(@PathVariable(name = "id") int id) {
		companyService.deleteParagraph(id);
	}
	
	
	@PostMapping("${rest.api.basePath}/companies/photos")
	void addPhoto(@RequestBody PhotoDto photoDto) throws IOException {
		companyService.addPhoto(photoDto);
	}
	
	@GetMapping("${rest.api.basePath}/companies/photos")
	Set<Photo> findPhotos() {
		return companyService.findPhotos();
	}
	
	@DeleteMapping("${rest.api.basePath}/companies/photos/{id}")
	void deletePhoto(@PathVariable(name = "id") int id) {
		companyService.deletePhoto(id);
	}
}
