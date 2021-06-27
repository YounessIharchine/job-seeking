package com.pfa.jobseeking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.seeker.Follow;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.AdminRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.service.NotificationService;


@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Transactional
	@Override
	public void resetAdminNotifications() {
		for(Admin admin : adminRepository.findAll()) {
			admin.getAdminNotification().resetNewCompanyCreationRequests();
			admin.getAdminNotification().resetNewOfferCreationRequests();
		}	
	}

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void resetCompanyNotifications() {
		getAuthenticatedCompany().getCompanyNotification().resetNewFollowers();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void resetFollowNotifications() {
		for(Follow follow : getAuthenticatedSeeker().getFollows())
			follow.getFollowNotification().resetNewOffers();
	}
	
	
	private Company getAuthenticatedCompany() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Company)userRepository.findUserByEmail(authenticatedUserEmail);
	}
	
	
	private Seeker getAuthenticatedSeeker() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
	}

}