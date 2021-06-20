package com.pfa.jobseeking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.CompanyRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.service.SeekerService;

@Service
public class SeekerServiceImpl implements SeekerService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void update(Map<String, String> map){
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
		
		if(map.containsKey("firstName"))
			seeker.setFirstName(map.get("firstName"));
		if(map.containsKey("lastName"))
			seeker.setLastName(map.get("lastName"));
		if(map.containsKey("phone"))
			seeker.setPhone(map.get("phone"));
		if(map.containsKey("address"))
			seeker.setAddress(map.get("address"));
		if(map.containsKey("birthDate"))
			seeker.setBirthDate(map.get("birthDate"));
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void save(int id) {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
		seeker.saveOffer(offerRepository.findById(id));
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void follow(String companyName) {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
		seeker.followCompany(companyRepository.findCompanyByName(companyName));
	}

	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Override
	public List<OfferResponse> findSavedOffers() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);

		return mapToResponse(seeker.getOffers());
	}
	

	private List<OfferResponse> mapToResponse(Set<Offer> offers) {
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
