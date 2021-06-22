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

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
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
	
	
	@PostMapping("/internshipTypes")
	List<InternshipType> addinternshipType(@RequestBody NameDto internshipType) throws AlreadyExistsException {
		
		return internshipTypeService.save(internshipType);
		
	}
	
	
	@DeleteMapping("/internshipTypes")
	List<InternshipType> removeinternshipType(@RequestParam("name") String internshipType) throws NotFoundException {
		
		return internshipTypeService.delete(internshipType);
		
	}
}
