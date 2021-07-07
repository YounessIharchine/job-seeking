package com.pfa.jobseeking.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.service.ApplicationService;

@RestController
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;
	
	@GetMapping(
			value = "/api/applications/{seekerId}/{offerId}/cv", 
			produces = MediaType.APPLICATION_PDF_VALUE
			)
	public @ResponseBody byte[] getCv(@PathVariable int seekerId, @PathVariable int offerId) throws IOException {
		return applicationService.getCv(seekerId, offerId);
	}
}
