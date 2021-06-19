package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.CompanyCreationRequest;
import com.pfa.jobseeking.model.OfferCreationRequest;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.repository.CompanyCreationRequestRepository;
import com.pfa.jobseeking.repository.CompanyRepository;
import com.pfa.jobseeking.repository.OfferCreationRequestRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.rest.response.CompanyCreationRequestResponse;
import com.pfa.jobseeking.rest.response.OfferCreationRequestResponse;
import com.pfa.jobseeking.service.CreationRequestService;

@Service
public class CreationRequestServiceImpl implements CreationRequestService{

	@Autowired
	CompanyCreationRequestRepository companyCreationRequestRepository;
	
	@Autowired
	OfferCreationRequestRepository offerCreationRequestRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<OfferCreationRequestResponse> findAllOfferCreationRequests() {
		List<OfferCreationRequest> requests = offerCreationRequestRepository.findAll();
		List<OfferCreationRequestResponse> response = new ArrayList<>();
		
		for(OfferCreationRequest request : requests) {
			OfferCreationRequestResponse item = new OfferCreationRequestResponse();
			item.setRequestDate(request.getDate());
			item.setDescription(request.getOffer().getDescription());
			item.setTitle(request.getOffer().getTitle());
			item.setOfferDate(request.getOffer().getDate());
			response.add(item);
		}
		
		return response;
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void acceptCompanyCreationOffer(String companyName) {
		Company company = companyRepository.findCompanyByName(companyName);
		company.setVerified(true);
		company.setCompanyCreationRequest(null);
		companyCreationRequestRepository.delete(companyCreationRequestRepository.findByCompanyName(companyName));
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void rejectCompanyCreationOffer(String companyName) {
		companyRepository.delete(companyRepository.findCompanyByName(companyName));
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public void acceptOfferCreationOffer(int id) {
		Offer offer = offerRepository.findById(id);
		offer.setVerified(true);
		offer.setOfferCreationRequest(null);
		offerCreationRequestRepository.delete(offerCreationRequestRepository.findByOfferId(id));
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public void rejectOfferCreationOffer(int id) {
		offerRepository.delete(offerRepository.findById(id));
	}

}
