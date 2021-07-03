package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.rest.dto.OfferDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerResponse;

public interface OfferService {

	//****************************OFFER SEARCH***************************

	List<OfferResponse> findAll(String domain, String keyword, String city, String internshipType, String jobType);

	OfferResponse findOffer(int id);

	
	//****************************OFFER PUBLICATION***************************

	void addInternshipOffer(OfferDto offerDto);

	void addJobOffer(OfferDto offerDto);
	
	
	//****************************OFFER APPLIERS***************************

	List<SeekerResponse> findAppliers(int id) throws AccessDeniedException, IOException;


}
