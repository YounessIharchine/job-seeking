package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;

public interface CreationRequestService {

	List<CompanyCreationRequestResponse> findAllCompanyCreationRequests() throws IOException;
	
}
