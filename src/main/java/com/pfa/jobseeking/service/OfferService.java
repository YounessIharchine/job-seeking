package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.rest.response.OfferResponse;

public interface OfferService {

	List<OfferResponse> findAll(String domain, String keyword, String city, String internshipType, String jobType);

	void save(int id);

}
