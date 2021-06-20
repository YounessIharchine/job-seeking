package com.pfa.jobseeking.service;

import java.util.List;
import java.util.Map;

import com.pfa.jobseeking.rest.response.OfferResponse;

public interface SeekerService {

	void update(Map<String, String> map);

	void save(int id);

	void follow(String companyName);

	List<OfferResponse> findSavedOffers();

}
