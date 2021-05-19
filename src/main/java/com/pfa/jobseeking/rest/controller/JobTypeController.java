package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.offer.JobType;
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
	
}
