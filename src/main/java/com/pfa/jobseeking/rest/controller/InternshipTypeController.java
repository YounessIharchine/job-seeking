package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.service.InternshipTypeService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class InternshipTypeController {

	@Autowired
	InternshipTypeService internshipTypeService;
	
	@GetMapping("/internshipTypes")
	List<InternshipType> showInternshipTypes() {
		return internshipTypeService.findAll();
	}
}
