package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.seeker.Experience;
import com.pfa.jobseeking.model.seeker.Language;
import com.pfa.jobseeking.rest.dto.ExperienceDto;
import com.pfa.jobseeking.rest.dto.LanguageDto;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.rest.response.SeekerAccountResponse;
import com.pfa.jobseeking.rest.response.SeekerProfileResponse;
import com.pfa.jobseeking.rest.response.SeekerStepOneResponse;
import com.pfa.jobseeking.service.SeekerService;

@RestController
public class SeekerController {

	@Autowired
	SeekerService seekerService;
	
	
	@GetMapping("${rest.api.basePath}/seekers/{id}")
	SeekerProfileResponse getSeeker(@PathVariable int id) {
		return seekerService.findSeeker(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/account")
	SeekerAccountResponse getSeekerAccount() {
		return seekerService.fetchSeekerAccount();
	}
	
	@GetMapping("${rest.api.basePath}/seekers/stepOne")
	SeekerStepOneResponse getSeekerStepOne() {
		return seekerService.fetchSeekerStepOne();
	}
	
	
	@PatchMapping("${rest.api.basePath}/seekers/profile")
	ResponseEntity<Response> updateInfo(@RequestBody Map<String, String> map) throws IOException {
		seekerService.updateInfo(map);
		
		Response response = new Response(HttpStatus.OK.value(), "Update Successful");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@GetMapping("${rest.api.basePath}/seekers/saveOffer/{id}")
	void saveOffer(@PathVariable int id) {
		seekerService.save(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/unsaveOffer/{id}")
	void unsaveOffer(@PathVariable int id) {
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
	
	
	//*****************EXPERIENCES*****************
	@GetMapping("${rest.api.basePath}/seekers/experiences")
	List<Experience> getExperiences() {
		return seekerService.findExperiences();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/experiences")
	List<Experience> addExperience(@RequestBody ExperienceDto experienceDto) {
		return seekerService.addExperience(experienceDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/experiences/{id}")
	List<Experience> removeExperience(@PathVariable int id) {
		return seekerService.deleteExperience(id);
	}
	
	
	//*****************LANGUAGES*****************
	@GetMapping("${rest.api.basePath}/seekers/languages")
	List<Language> getLanguages() {
		return seekerService.findLanguages();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/languages")
	List<Language> addLanguage(@RequestBody LanguageDto languageDto) {
		return seekerService.addLanguage(languageDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/languages/{id}")
	List<Language> removeLanguage(@PathVariable int id) {
		return seekerService.deleteLanguage(id);
	}
	
	
}
