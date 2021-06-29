package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.rest.dto.OfferDto;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.service.OfferService;


@RestController
public class OfferController {

	@Autowired
	OfferService offerService;
	
	//****************************OFFER SEARCH***************************
	@GetMapping("${rest.api.basePath}/offers")
	List<OfferResponse> showOffers(
			@RequestParam(required = false) String domain,
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String internshipType,
			@RequestParam(required = false) String jobType) {
		
		return offerService.findAll(domain, keyword, city, internshipType, jobType);
		
	}
	
	
	
	//****************************OFFER PUBLICATION***************************
	@PostMapping("${rest.api.basePath}/internshipOffers")
	void addInternshipOffer(@RequestBody OfferDto offerDto) {
		offerService.addInternshipOffer(offerDto);
	}
	
	@PostMapping("${rest.api.basePath}/jobOffers")
	void addJobOffer(@RequestBody OfferDto offerDto) {
		offerService.addJobOffer(offerDto);
	}
	
	
	
}







//@GetMapping("/offers")
//List<Offer> listOffers(@RequestParam(required = false) Integer page, 
//@RequestParam(required = false, defaultValue = "20") Integer limit, 
//@RequestParam(required = false) String[] sort) 
//		throws NotFoundException {
//
//	if(page == null)
//		return offerService.findAll();
//	else if(sort == null)
//		return offerService.findAll(PageRequest.of(page, limit));
//	
//	List<Order> orders = new ArrayList<>();
//	for(String field : sort) {
//		
//		if(field.contains("desc"))
//			orders.add(Sort.Order.desc(field.substring(0, field.length()-4)));
//		else if(field.contains("asc"))
//			orders.add(Sort.Order.asc(field.substring(0, field.length()-3)));
//		else
//			orders.add(Sort.Order.asc(field));
//	}
//	
//	return offerService.findAll(PageRequest.of(page, limit, Sort.by(orders)));
//}
