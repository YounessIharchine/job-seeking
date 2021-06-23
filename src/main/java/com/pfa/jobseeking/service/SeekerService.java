package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerProfileResponse;

public interface SeekerService {

	void updateInfo(Map<String, String> map) throws IOException;

	void save(int id);
	
	void unsave(int id);

	void follow(String companyName);
	
	void unfollow(String companyName);

	List<OfferResponse> findSavedOffers();

	SeekerProfileResponse findSeeker(int id);


}
