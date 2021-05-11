package com.pfa.jobseeking.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.OfferService;

@RestController
@RequestMapping("${rest.api.basePath}")
public class OfferController {

	@Autowired
	OfferService offerService;
	
	
	@GetMapping("/offers")
	List<Offer> listOffers(@RequestParam(required = false) Integer page, 
	@RequestParam(required = false, defaultValue = "20") Integer limit, 
	@RequestParam(required = false) String[] sort,
	@RequestParam(required = false) String[] direction) 
			throws NotFoundException {

		if(page == null)
			return offerService.findAll();
		else if(sort == null)
			return offerService.findAll(PageRequest.of(page, limit));
		
		List<Order> orders = new ArrayList<>();
		for(String field : sort) {
			
			if(field.contains("desc"))
				orders.add(Sort.Order.desc(field.substring(0, field.length()-4)));
			else if(field.contains("asc"))
				orders.add(Sort.Order.asc(field.substring(0, field.length()-3)));
			else
				orders.add(Sort.Order.asc(field));
		}
		
		return offerService.findAll(PageRequest.of(page, limit, Sort.by(orders)));
	}
	
}
