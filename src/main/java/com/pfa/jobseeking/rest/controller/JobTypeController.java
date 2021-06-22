package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	List<JobType> addjobType(@RequestBody NameDto jobType) throws AlreadyExistsException {
		
		return jobTypeService.save(jobType);
		
	}
	
	
	@DeleteMapping("/jobTypes")
	List<JobType> removejobType(@RequestParam("name") String jobType) throws NotFoundException {
		
		return jobTypeService.delete(jobType);
		
	}
}
