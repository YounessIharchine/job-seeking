package com.pfa.jobseeking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.InternshipOfferRepository;
import com.pfa.jobseeking.repository.JobOfferRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.service.OfferService;


@Service
public class OfferServiceImpl implements OfferService {

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	InternshipOfferRepository internshipOfferRepository;
	
	@Autowired
	JobOfferRepository jobOfferRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<OfferResponse> findAll(String domain, String keyword, String city, String internshipType, String jobType) {
		
		if(domain == null) {
			
			if(keyword == null) {
				
				if(city == null) {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findAll());
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByJobTypeName(jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByInternshipTypeName(internshipType));
					}
					
				}
				
				else {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByCityName(city));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByCityANDType(city, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByCityANDType(city, internshipType));
					}
					
				}
				
			}
			
			else {
				
				if(city == null) {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByKeyword(keyword));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByKeywordANDType(keyword, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByKeywordANDType(keyword, internshipType));
					}
					
				}
				
				else {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByKeywordANDCity(keyword, city));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByKeywordANDCityANDType(keyword, city, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByKeywordANDCityANDType(keyword, city, internshipType));
					}

				}
				
			}
			
		}
		else {
			
			if(keyword == null) {
				
				if(city == null) {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByDomainName(domain));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByDomainANDType(domain, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByDomainANDType(domain, internshipType));
					}
					
				}
				
				else {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByDomainNameANDCity(domain, city));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByDomainANDCityANDType(domain, city, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByDomainANDCityANDType(domain, city, internshipType));
					}

				}
				
				
			}
			
			else {
				
				if(city == null) {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByDomainNameANDKeyword(domain, keyword));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByDomainANDKeywordANDType(domain, keyword, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByDomainANDKeywordANDType(domain, keyword, internshipType));
					}

				}
				
				else {
					
					if(internshipType == null && jobType == null) {
						return mapToResponse(offerRepository.findByDomainNameANDKeywordANDCity(domain, keyword, city));
					}
					
					else if(internshipType == null) {
						return mapToResponse(jobOfferRepository.findByDomainANDKeywordANDCityANDType(domain, keyword, city, jobType));
					}
					
					else {
						return mapToResponse(internshipOfferRepository.findByDomainANDKeywordANDCityANDType(domain, keyword, city, internshipType));
					}

				}
				
			}
			
		}

	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void save(int id) {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
		seeker.saveOffer(offerRepository.findById(id));
	}
	
	
	private List<OfferResponse> mapToResponse(List<Offer> offers) {
		List<OfferResponse> response = new ArrayList<>();
		for(Offer offer : offers) {
			OfferResponse item = new OfferResponse();
			item.setId(offer.getId());
			item.setTitle(offer.getTitle());
			item.setDescription(offer.getDescription());
			item.setDate(offer.getDate());
			item.setCity(offer.getCity().getName());
			item.setDomain(offer.getDomain().getName());
			item.setCompanyName(offer.getCompany().getName());
			if(offer instanceof InternshipOffer) {
				InternshipOffer internshipOffer = (InternshipOffer) offer;
				item.setInternshipOffer(true);
				item.setType(internshipOffer.getInternshipType().getName());
				item.setDuration(internshipOffer.getDuration().getDuration());
			}
			else if(offer instanceof JobOffer) {
				JobOffer jobOffer = (JobOffer) offer;
				item.setInternshipOffer(false);
				item.setType(jobOffer.getJobType().getName());
				item.setDuration(null);
			}
				
			response.add(item);
		}
		return response;
	}




}



