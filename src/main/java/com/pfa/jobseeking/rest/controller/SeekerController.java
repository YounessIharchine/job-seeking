package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.rest.response.SeekerAccountResponse;
import com.pfa.jobseeking.rest.response.SeekerProfileResponse;
import com.pfa.jobseeking.service.SeekerService;

@RestController
public class SeekerController {

	@Autowired
	SeekerService seekerService;
	
	
	@GetMapping("${rest.api.basePath}/seekers/{id}")
	SeekerProfileResponse getSeeker(@PathVariable(name = "id") int id) {
		return seekerService.findSeeker(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/account")
	SeekerAccountResponse getSeekerAccount() {
		return seekerService.fetchSeekerAccount();
	}
	
	
	@PatchMapping("${rest.api.basePath}/seekers/profile")
	ResponseEntity<Response> updateInfo(@RequestBody Map<String, String> map) throws IOException {
		seekerService.updateInfo(map);
		
		Response response = new Response(HttpStatus.OK.value(), "Update Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("${rest.api.basePath}/seekers/saveOffer/{id}")
	void saveOffer(@PathVariable(name = "id") int id) {
		seekerService.save(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/unsaveOffer/{id}")
	void unsaveOffer(@PathVariable(name = "id") int id) {
		seekerService.unsave(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/followCompany")
	void followCompany(@RequestParam String companyName) {
		seekerService.follow(companyName);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/unfollowCompany")
	void unfollowCompany(@RequestParam String companyName) {
		seekerService.unfollow(companyName);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/savedOffers")
	List<OfferResponse> showSavedOffers() {
		return seekerService.findSavedOffers();
	}
	
}
