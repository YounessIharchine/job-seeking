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

import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.Response;
import com.pfa.jobseeking.service.DomainService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class DomainController {
	
	@Autowired
	DomainService domainService;
	
	@GetMapping("/domains")
	List<Domain> listDomains() {
		
		return domainService.findAll();
	}
	
	@PostMapping("/domains")
	ResponseEntity<Response> addDomain(@RequestBody String domain) throws AlreadyExistsException {
		
		domainService.save(domain);
		
		Response response = new Response(HttpStatus.OK.value(), "Domain successfully added");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/domains")
	ResponseEntity<Response> removeDomain(@RequestParam("name") String domain) throws NotFoundException {
		
		domainService.delete(domain);
		
		Response response = new Response(HttpStatus.OK.value(), "Domain successfully deleted");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	

}






//@GetMapping("/domains")
//List<Domain> listDomains(@RequestParam(required = false) Integer page, 
//		@RequestParam(required = false, defaultValue = "20") Integer limit, 
//		@RequestParam(required = false) String[] sort) 
//				throws NotFoundException {
//	
//	if(page == null)
//		return domainService.findAll();
//	else if(sort == null)
//		return domainService.findAll(PageRequest.of(page, limit));
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
//	return domainService.findAll(PageRequest.of(page, limit, Sort.by(orders)));
//	
//}
