package com.pfa.jobseeking.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.AdminNotification;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.Follow;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.AdminRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.response.ApplicationNotificationResponse;
import com.pfa.jobseeking.rest.response.CompanyNotificationResponse;
import com.pfa.jobseeking.rest.response.FollowNotificationResponse;
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
	public AdminNotification getAdminNotifications() {
		return getAuthenticatedAdmin().getAdminNotification();
	}
	
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
	public CompanyNotificationResponse getCompanyNotifications() {
		CompanyNotificationResponse response = new CompanyNotificationResponse();
		response.setNewFollowers(getAuthenticatedCompany().getCompanyNotification().getNewFollowers());
		
		for(Offer offer : getAuthenticatedCompany().getOffers()) {
			ApplicationNotificationResponse item = new ApplicationNotificationResponse();
			item.setOfferTitle(offer.getTitle());
			item.setNewApplications(offer.getApplicationNotification().getNewApplications());
			response.addApplicationNotification(item);
		}
		
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void resetCompanyNotifications() {
		getAuthenticatedCompany().getCompanyNotification().resetNewFollowers();
		
		for(Offer offer : getAuthenticatedCompany().getOffers())
			offer.getApplicationNotification().resetNewApplications();
	}

	
	
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<FollowNotificationResponse> getFollowNotifications() {
		List<FollowNotificationResponse> response = new ArrayList<FollowNotificationResponse>();
		
		for(Follow follow : getAuthenticatedSeeker().getFollows()) {
			FollowNotificationResponse item = new FollowNotificationResponse();
			item.setCompanyName(follow.getCompany().getName());
			item.setNewOffers(follow.getFollowNotification().getNewOffers());
			response.add(item);
		}
		
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void resetFollowNotifications() {
		for(Follow follow : getAuthenticatedSeeker().getFollows())
			follow.getFollowNotification().resetNewOffers();
	}
	
	
	
	
	
	private Admin getAuthenticatedAdmin() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Admin)userRepository.findUserByEmail(authenticatedUserEmail);
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
