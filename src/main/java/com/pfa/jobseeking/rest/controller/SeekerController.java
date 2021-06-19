package com.pfa.jobseeking.rest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.service.SeekerService;

@RestController
public class SeekerController {

	@Autowired
	SeekerService seekerService;
	
	@PatchMapping("/updateInfo")
	ResponseEntity<Response> patch(@RequestBody Map<String, String> map){
		seekerService.update(map);
		
		Response response = new Response(HttpStatus.OK.value(), "Update Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/saveOffer/{id}")
	void saveOffer(@PathVariable(name = "id") int id) {
		seekerService.save(id);
	}
}
