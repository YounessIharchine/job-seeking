package com.pfa.jobseeking.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferService {

	List<Offer> findAll(String domain, String keyword);

	List<Offer> findAll(Pageable pageable);

}
