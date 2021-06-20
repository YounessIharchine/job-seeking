package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pfa.jobseeking.rest.response.OfferResponse;

public interface SeekerService {

	void updateInfo(Map<String, String> map) throws IOException;

	void save(int id);

	void follow(String companyName);

	List<OfferResponse> findSavedOffers();

}
