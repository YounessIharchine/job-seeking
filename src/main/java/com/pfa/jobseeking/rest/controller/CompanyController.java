package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.rest.dto.ParagraphDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;
import com.pfa.jobseeking.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	
	//**********************************FIND COMPANIES**********************************
	@GetMapping("${rest.api.basePath}/companies")
	List<FindCompanyResponse> getCompanies(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String domain) throws IOException {
		return companyService.findCompanies(name, domain);
	}
	
	//**********************************PUBLIC PROFILE**********************************
	@GetMapping("${rest.api.basePath}/companies/{id}")
	CompanyResponse getCompany(@PathVariable int id) throws IOException, NotFoundException {
		return companyService.findCompany(id);
	}
	
	@GetMapping("${rest.api.basePath}/companies/{id}/logo")
	PhotoDto getSeekerPhoto(@PathVariable int id) throws IOException, NotFoundException {
		return companyService.findCompanyLogo(id);
	}
	
	
	//**********************************OWN PROFILE**********************************
	@PatchMapping("${rest.api.basePath}/companies/profile")
	CompanyResponse updateCompanyInfo(@RequestBody CompanyResponse companyResponse) throws IOException {
		return companyService.updateInfo(companyResponse);
	}
	
	@PatchMapping("${rest.api.basePath}/companies/profile/photo")
	PhotoDto updatePhoto(@RequestBody PhotoDto photoDto) throws IOException {
		return companyService.updateLogo(photoDto);
	}
	
	
	//**********************************PARAGRAPHS**********************************
	@GetMapping("${rest.api.basePath}/companies/paragraphs")
	List<Paragraph> findParagraphs() {
		return companyService.findParagraphs();
	}
	
	@PostMapping("${rest.api.basePath}/companies/paragraphs")
	List<Paragraph> addParagraph(@RequestBody ParagraphDto paragraphDto) {
		return companyService.addParagraph(paragraphDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/companies/paragraphs/{id}")
	List<Paragraph> deleteParagraph(@PathVariable(name = "id") int id) throws AccessDeniedException {
		return companyService.deleteParagraph(id);
	}
	
	
	//**********************************PHOTOS**********************************
	@GetMapping("${rest.api.basePath}/companies/photos")
	Set<Photo> findPhotos() {
		return companyService.findPhotos();
	}
	
	@PostMapping("${rest.api.basePath}/companies/photos")
	void addPhoto(@RequestBody PhotoDto photoDto) throws IOException {
		companyService.addPhoto(photoDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/companies/photos/{id}")
	void deletePhoto(@PathVariable(name = "id") int id) throws AccessDeniedException {
		companyService.deletePhoto(id);
	}
	
	
	
}
