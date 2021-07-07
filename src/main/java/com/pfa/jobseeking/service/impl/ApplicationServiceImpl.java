package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.Application;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.ApplicationRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public byte[] getCv(int seekerId, int offerId) throws IOException {
//		Company company = getAuthenticatedCompany();
		Seeker seeker = (Seeker)userRepository.findById(seekerId);
		Offer offer = offerRepository.findById(offerId);
		
		Application application = applicationRepository.findBySeekerAndOffer(seeker, offer);
		
//		if(!isApplicationOwner(company, paragraph))
//			throw new AccessDeniedException("You don't own this paragraph.");
		return FileUtils.readFileToByteArray(new File(path+application.getCv()));
	}

	@Override
	public byte[] getCoverLetter(int seekerId, int offerId) throws IOException {
//		Company company = getAuthenticatedCompany();
		Seeker seeker = (Seeker)userRepository.findById(seekerId);
		Offer offer = offerRepository.findById(offerId);
		
		Application application = applicationRepository.findBySeekerAndOffer(seeker, offer);
		
//		if(!isApplicationOwner(company, paragraph))
//			throw new AccessDeniedException("You don't own this paragraph.");
		return FileUtils.readFileToByteArray(new File(path+application.getCoverLetter()));
	}

	
	
	
//	private Company getAuthenticatedCompany() {
//		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//		return (Company)userRepository.findUserByEmail(authenticatedUserEmail);
//	}
}
