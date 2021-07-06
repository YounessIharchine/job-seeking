package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.rest.response.OfferCreationRequestResponse;
import com.pfa.jobseeking.service.CreationRequestService;

@RestController
public class CreationRequestController {

	@Autowired
	CreationRequestService creationRequestService;
	
	@GetMapping("${rest.api.basePath}/companyCreationRequests")
	List<CompanyCreationRequestResponse> showCompanyCreationRequests() throws IOException {
		return creationRequestService.findAllCompanyCreationRequests();
	}
	
	@GetMapping("${rest.api.basePath}/offerCreationRequests")
	List<OfferCreationRequestResponse> showOfferCreationRequests() {
		return creationRequestService.findAllOfferCreationRequests();
	}
	
	
	
	@GetMapping("${rest.api.basePath}/companyCreationRequests/accept/{id}")
	List<CompanyCreationRequestResponse> acceptCompanyCreationRequest(@PathVariable int id) {
		return creationRequestService.acceptCompanyCreationOffer(id);
	}
	
	@GetMapping("${rest.api.basePath}/companyCreationRequests/reject/{id}")
	List<CompanyCreationRequestResponse> rejectCompanyCreationRequest(@PathVariable int id) {
		return creationRequestService.rejectCompanyCreationOffer(id);
	}
	
	
	
	@GetMapping("${rest.api.basePath}/offerCreationRequests/accept/{id}")
	List<OfferCreationRequestResponse> acceptOfferCreationRequest(@PathVariable int id) {
		return creationRequestService.acceptOfferCreationOffer(id);
	}
	
	@GetMapping("${rest.api.basePath}/offerCreationRequests/reject/{id}")
	List<OfferCreationRequestResponse> rejectOfferCreationRequest(@PathVariable int id) {
		return creationRequestService.rejectOfferCreationOffer(id);
	}
	
	
	
	@GetMapping(
			value = "${rest.api.basePath}/companyCreationRequests/{id}/document", 
			produces = MediaType.APPLICATION_PDF_VALUE
			)
	public @ResponseBody byte[] getDocument(@PathVariable int id) throws IOException {
		return creationRequestService.getDocument(id);
	}
}
