package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.service.OfferService;


@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;
	
	@Override
	public List<Offer> findAll(String domain, String keyword) {
		if(domain == null) {
			if(keyword == null) {
				return offerRepository.findAll();
			}
			else {
				return offerRepository.findOfferByTitleContainsOrDescriptionContains(keyword, keyword);
			}
		}
		else {
			if(keyword == null) {
				return offerRepository.findOfferByDomainName(domain);
			}
			else {
				return offerRepository.findOfferByDomainNameAndTitleContainsOrDomainNameAndDescriptionContains(domain, keyword, domain, keyword);
			}
		}

	}

}









//@Override
//public List<Offer> findAll(Pageable pageable) {
//	return offerRepository.findAll(pageable).getContent();
//}
