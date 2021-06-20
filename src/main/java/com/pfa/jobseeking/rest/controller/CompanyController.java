package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	CompanyService companyService;

	@PatchMapping("/updateCompanyInfo")
	ResponseEntity<Response> updateCompanyInfo(@RequestBody Map<String, String> map) throws IOException{
		companyService.updateInfo(map);
		
		Response response = new Response(HttpStatus.OK.value(), "Update Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
