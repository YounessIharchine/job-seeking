package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("/acceptCompanyCreationRequest")
	void acceptCompanyCreationRequest(@RequestParam String companyName) {
		creationRequestService.acceptCompanyCreationOffer(companyName);
	}
	
	@GetMapping("/rejectCompanyCreationRequest")
	void rejectCompanyCreationRequest(@RequestParam String companyName) {
		creationRequestService.rejectCompanyCreationOffer(companyName);
	}
	
	@GetMapping("/acceptOfferCreationRequest/{id}")
	void acceptOfferCreationRequest(@PathParam(value = "id") int id) {
		creationRequestService.acceptOfferCreationOffer(id);
	}
	
	@GetMapping("/rejectOfferCreationRequest/{id}")
	void rejectOfferCreationRequest(@PathParam(value = "id") int id) {
		creationRequestService.rejectOfferCreationOffer(id);
	}
	
}
