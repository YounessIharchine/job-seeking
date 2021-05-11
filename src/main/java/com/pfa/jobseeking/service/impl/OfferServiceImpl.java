package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.service.OfferService;


@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;
	
	@Override
	public List<Offer> findAll() {
		return offerRepository.findAll();
	}

	@Override
	public List<Offer> findAll(Pageable pageable) {
		return offerRepository.findAll(pageable).getContent();
	}

}
