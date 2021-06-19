package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.service.JobTypeService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class JobTypeController {

	@Autowired
	JobTypeService jobTypeService;
	
	
	@GetMapping("/jobTypes")
	List<JobType> showJobTypes() {
		return jobTypeService.findAll();
	}
	
	
	@PostMapping("/jobTypes")
	ResponseEntity<Response> addjobType(@RequestBody NameDto jobType) throws AlreadyExistsException {
		
		jobTypeService.save(jobType);
		
		Response response = new Response(HttpStatus.OK.value(), "Job Type successfully added");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/jobTypes")
	ResponseEntity<Response> removejobType(@RequestParam("name") String jobType) throws NotFoundException {
		
		jobTypeService.delete(jobType);
		
		Response response = new Response(HttpStatus.OK.value(), "Job Type successfully deleted");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
}
