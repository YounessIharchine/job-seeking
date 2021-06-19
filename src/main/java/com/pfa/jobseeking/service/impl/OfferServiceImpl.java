package com.pfa.jobseeking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.service.OfferService;


@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;
	
	@Override
	public List<OfferResponse> findAll(String domain, String keyword) {
		if(domain == null) {
			if(keyword == null) {
				return mapToResponse(offerRepository.findAll());
			}
			else {
				return mapToResponse(offerRepository.findByKeyword(keyword));
			}
		}
		else {
			if(keyword == null) {
				return mapToResponse(offerRepository.findByDomainName(domain));
			}
			else {
				return mapToResponse(offerRepository.findByKeywordANDDomainName(keyword, domain));
			}
		}

	}
	
	
	private List<OfferResponse> mapToResponse(List<Offer> offers) {
		List<OfferResponse> response = new ArrayList<>();
		for(Offer offer : offers) {
			OfferResponse item = new OfferResponse();
			item.setTitle(offer.getTitle());
			item.setDescription(offer.getDescription());
			item.setDate(offer.getDate());
			item.setCity(offer.getCity().getName());
			item.setDomain(offer.getDomain().getName());
			item.setCompanyName(offer.getCompany().getName());
			response.add(item);
		}
		return response;
	}

}









//@Override
//public List<Offer> findAll(Pageable pageable) {
//	return offerRepository.findAll(pageable).getContent();
//}
