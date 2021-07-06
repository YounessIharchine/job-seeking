package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		return mapToCompanyResponse(companyCreationRequestRepository.findAll());
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<OfferCreationRequestResponse> findAllOfferCreationRequests() {
		return mapToOfferResponse(offerCreationRequestRepository.findAll());
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public List<CompanyCreationRequestResponse> acceptCompanyCreationOffer(int id) {
		Company company = companyRepository.findById(id);
		company.setVerified(true);
		company.setCompanyCreationRequest(null);
		companyCreationRequestRepository.delete(companyCreationRequestRepository.findByCompanyId(id));
		return mapToCompanyResponse(companyCreationRequestRepository.findAll());
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public List<CompanyCreationRequestResponse> rejectCompanyCreationOffer(int id) {
		companyRepository.delete(companyRepository.findById(id));
		return mapToCompanyResponse(companyCreationRequestRepository.findAll());
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<OfferCreationRequestResponse> acceptOfferCreationOffer(int id) {
		Offer offer = offerRepository.findById(id);
		offer.setVerified(true);
		offer.setOfferCreationRequest(null);
		offerCreationRequestRepository.delete(offerCreationRequestRepository.findByOfferId(id));
		return mapToOfferResponse(offerCreationRequestRepository.findAll());
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public List<OfferCreationRequestResponse> rejectOfferCreationOffer(int id) {
		offerRepository.delete(offerRepository.findById(id));
		return mapToOfferResponse(offerCreationRequestRepository.findAll());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	public byte[] getDocument(int id) throws IOException {
		return FileUtils.readFileToByteArray(new File(path+companyCreationRequestRepository.findById(id).getCompany().getDocumentPath()));
	}
	
	
	
	
	
	
	private List<CompanyCreationRequestResponse> mapToCompanyResponse(List<CompanyCreationRequest> requests) {
		List<CompanyCreationRequestResponse> response = new ArrayList<>();
		
		for(CompanyCreationRequest request : requests) {
			CompanyCreationRequestResponse item = new CompanyCreationRequestResponse();
			item.setId(request.getCompany().getId());
			item.setDate(request.getDate());
			item.setCompanyName(request.getCompany().getName());
			response.add(item);
		}
		
		return response;
	}
	
	
	private List<OfferCreationRequestResponse> mapToOfferResponse(List<OfferCreationRequest> requests) {
		List<OfferCreationRequestResponse> response = new ArrayList<>();
		
		for(OfferCreationRequest request : requests) {
			OfferCreationRequestResponse item = new OfferCreationRequestResponse();
			item.setId(request.getOffer().getId());
			item.setRequestDate(request.getDate());
			item.setDescription(request.getOffer().getDescription());
			item.setTitle(request.getOffer().getTitle());
			response.add(item);
		}
		
		return response;
	}

}
