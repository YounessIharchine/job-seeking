package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.rest.response.OfferCreationRequestResponse;

public interface CreationRequestService {

	List<CompanyCreationRequestResponse> findAllCompanyCreationRequests() throws IOException;
	
	List<OfferCreationRequestResponse> findAllOfferCreationRequests();

	void acceptCompanyCreationOffer(String companyName);

	void rejectCompanyCreationOffer(String companyName);
	
}
