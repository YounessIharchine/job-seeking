package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.service.CityService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class CityController {

	@Autowired
	CityService cityService;
	
	
	@GetMapping("/cities")
	List<City> listCities() {
		
		return cityService.findAll();
	}
		
}




//@GetMapping("/cities")
//List<City> listCities(@RequestParam(required = false) Integer page, 
//		@RequestParam(required = false, defaultValue = "20") Integer limit, 
//		@RequestParam(required = false) String[] sort) 
//				throws NotFoundException {
//	
//	if(page == null)
//		return cityService.findAll();
//	else if(sort == null)
//		return cityService.findAll(PageRequest.of(page, limit));
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
//	return cityService.findAll(PageRequest.of(page, limit, Sort.by(orders)));