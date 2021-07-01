package com.pfa.jobseeking.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.ApplicationNotification;
import com.pfa.jobseeking.model.OfferCreationRequest;
import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.Follow;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.repository.AdminRepository;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.DurationRepository;
import com.pfa.jobseeking.repository.InternshipOfferRepository;
import com.pfa.jobseeking.repository.InternshipTypeRepository;
import com.pfa.jobseeking.repository.JobOfferRepository;
import com.pfa.jobseeking.repository.JobTypeRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.OfferDto;
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
	InternshipTypeRepository internshipTypeRepository;
	
	@Autowired
	JobTypeRepository jobTypeRepository;
	
	@Autowired
	DurationRepository durationRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	
	//****************************OFFER SEARCH***************************

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
	

	@Override
	public OfferResponse findOffer(int id) {
		Offer offer = offerRepository.findById(id);

		return mapToResponse(offer);
	}
	
	//****************************OFFER PUBLICATION***************************

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void addInternshipOffer(OfferDto offerDto) {
		Company company = getAuthenticatedCompany();
				
		InternshipOffer internshipOffer = (InternshipOffer)mapOfferDto(offerDto, true);
		internshipOffer.setInternshipType(internshipTypeRepository.findInternshipTypeByName(offerDto.getType()));
		internshipOffer.setDuration(durationRepository.findByDuration(offerDto.getDuration()));
		internshipOffer.setCompany(company);
		ApplicationNotification applicationNotification = new ApplicationNotification();
		internshipOffer.setApplicationNotification(applicationNotification);
		
		generateOfferCreationRequest(internshipOffer);
		
		incrementAdminNotif();
		
		incrementFollowNotif(internshipOffer);
		
		offerRepository.save(internshipOffer);
	}
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void addJobOffer(OfferDto offerDto) {
		Company company = getAuthenticatedCompany();

		JobOffer jobOffer = (JobOffer)mapOfferDto(offerDto, false);
		jobOffer.setJobType(jobTypeRepository.findJobTypeByName(offerDto.getType()));
		jobOffer.setCompany(company);
		ApplicationNotification applicationNotification = new ApplicationNotification();
		jobOffer.setApplicationNotification(applicationNotification);
		
		generateOfferCreationRequest(jobOffer);
		
		incrementAdminNotif();
		
		incrementFollowNotif(jobOffer);
		
		offerRepository.save(jobOffer);
	}
	
	
	
	
	
	
	
	//****************************PRIVATE METHODS***************************

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
	
	private OfferResponse mapToResponse(Offer offer) {
		OfferResponse response = new OfferResponse();

		response.setId(offer.getId());
		response.setTitle(offer.getTitle());
		response.setDescription(offer.getDescription());
		response.setDate(offer.getDate());
		response.setCity(offer.getCity().getName());
		response.setDomain(offer.getDomain().getName());
		response.setCompanyName(offer.getCompany().getName());
		if(offer instanceof InternshipOffer) {
			InternshipOffer internshipOffer = (InternshipOffer) offer;
			response.setInternshipOffer(true);
			response.setType(internshipOffer.getInternshipType().getName());
			response.setDuration(internshipOffer.getDuration().getDuration());
		}
		else if(offer instanceof JobOffer) {
			JobOffer jobOffer = (JobOffer) offer;
			response.setInternshipOffer(false);
			response.setType(jobOffer.getJobType().getName());
			response.setDuration(null);
		}
				
		return response;
	}

	
	private Offer mapOfferDto(OfferDto offerDto, boolean isInternship) {
		
		Offer offer;
		
		if(isInternship)
			offer = new InternshipOffer();
		else
			offer = new JobOffer();
		
		offer.setTitle(offerDto.getTitle());
		offer.setDescription(offerDto.getDescription());
		offer.setDate(offerDto.getDate());
		offer.setCity(cityRepository.findCityByName(offerDto.getCity()));
		offer.setDomain(domainRepository.findDomainByName(offerDto.getDomain()));
		offer.setOpen(true);
		return offer;
	}
	
	private void generateOfferCreationRequest(Offer offer) {
		OfferCreationRequest request = new OfferCreationRequest();
		request.setOffer(offer);
		request.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		offer.setOfferCreationRequest(request);
	}

	private void incrementAdminNotif() {
		for(Admin admin : adminRepository.findAll())
			admin.getAdminNotification().incrementNewCompanyCreationRequests();
	}
	
	private void incrementFollowNotif(Offer offer) {
		Company company = offer.getCompany();
		for(Follow follow : company.getFollows())
			follow.getFollowNotification().incrementNewOffers();
	}
	
	private Company getAuthenticatedCompany() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Company)userRepository.findUserByEmail(authenticatedUserEmail);
	}

}



