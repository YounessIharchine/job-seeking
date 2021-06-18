package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.service.CreationRequestService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class CreationRequestController {

	@Autowired
	CreationRequestService creationRequestService;
	
	@GetMapping("/companyCreationRequests")
	List<CompanyCreationRequestResponse> showCompanyCreationRequests() throws IOException {
		return creationRequestService.findAllCompanyCreationRequests();
	}
	
}
