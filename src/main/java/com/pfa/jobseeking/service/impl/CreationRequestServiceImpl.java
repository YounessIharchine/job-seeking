package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.CompanyCreationRequest;
import com.pfa.jobseeking.repository.CompanyCreationRequestRepository;
import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.service.CreationRequestService;

@Service
public class CreationRequestServiceImpl implements CreationRequestService{

	@Autowired
	CompanyCreationRequestRepository companyCreationRequestRepository;
	
	@Autowired
	OfferCreationRequestRepository offerCreationRequestRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	@Override
	public List<CompanyCreationRequestResponse> findAllCompanyCreationRequests() throws IOException {
		List<CompanyCreationRequest> requests = companyCreationRequestRepository.findAll();
		List<CompanyCreationRequestResponse> response = new ArrayList<>();
		
		for(CompanyCreationRequest request : requests) {
			CompanyCreationRequestResponse item = new CompanyCreationRequestResponse();
			item.setDate(request.getDate());
			item.setCompanyName(request.getCompany().getName());
			byte[] logoBytes = FileUtils.readFileToByteArray(new File(path+request.getCompany().getCompanyProfile().getLogo()));
			String encodedLogo =  Base64.getEncoder().encodeToString(logoBytes);
//			byte[] documentBytes = FileUtils.readFileToByteArray(new File(path+request.getCompany().getDocumentPath()));
//			String encodedDocument =  Base64.getEncoder().encodeToString(documentBytes);
			item.setEncodedLogo(encodedLogo);
			item.setEncodedDocument(null);
			response.add(item);
		}
		
		return response;
	}
}
