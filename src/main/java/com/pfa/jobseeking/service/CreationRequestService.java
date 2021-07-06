package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.rest.response.OfferCreationRequestResponse;

public interface CreationRequestService {

	List<CompanyCreationRequestResponse> findAllCompanyCreationRequests() throws IOException;
	
	List<OfferCreationRequestResponse> findAllOfferCreationRequests();

	List<CompanyCreationRequestResponse> acceptCompanyCreationOffer(int id);

	List<CompanyCreationRequestResponse> rejectCompanyCreationOffer(int id);

	List<OfferCreationRequestResponse> acceptOfferCreationOffer(int id);

	List<OfferCreationRequestResponse> rejectOfferCreationOffer(int id);

	byte[] getDocument(int id) throws IOException;
	
}
