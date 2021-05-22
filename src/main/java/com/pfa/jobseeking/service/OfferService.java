package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferService {

	List<Offer> findAll(String domain, String keyword);

}
