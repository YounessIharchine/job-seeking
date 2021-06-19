package com.pfa.jobseeking.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.SeekerService;

@Service
public class SeekerServiceImpl implements SeekerService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	
	@Transactional
	@Override
	public void update(Map<String, String> map, int id) throws NotFoundException {
		Seeker seeker = (Seeker) userRepository.findById(id);
		
		if(seeker == null)
			throw new NotFoundException("There is no seeker with that id");
		else {
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
		
		userRepository.save(seeker);
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void save(int id) {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Seeker seeker = (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
		seeker.saveOffer(offerRepository.findById(id));
	}
}
