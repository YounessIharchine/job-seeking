package com.pfa.jobseeking.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.AdminNotification;
import com.pfa.jobseeking.rest.response.CompanyNotificationResponse;
import com.pfa.jobseeking.rest.response.FollowNotificationResponse;
import com.pfa.jobseeking.service.NotificationService;

@RestController
public class NotificationController {

	@Autowired
	NotificationService notificationService;
	
	//******************ADMIN NOTIFICATION******************
	@GetMapping("/api/adminNotifications")
	AdminNotification getAdminNotifications() {
		return notificationService.getAdminNotifications();
	}
	
	@GetMapping("/resetAdminNotifications")
	void resetAdminNotifications() {
		notificationService.resetAdminNotifications();
	}
	
	
	
	//******************COMPANY NOTIFICATIONS******************
	@GetMapping("/api/companyNotifications")
	CompanyNotificationResponse getCompanyNotifications() {
		return notificationService.getCompanyNotifications();
	}
	
	@GetMapping("/resetCompanyNotifications")
	void resetCompanyNotifications() {
		notificationService.resetCompanyNotifications();
	}
	
	
	
	//******************FOLLOW NOTIFICATION******************
	@GetMapping("/api/followNotifications")
	List<FollowNotificationResponse> getFollowNotifications() {
		return notificationService.getFollowNotifications();
	}
	
	@GetMapping("/resetFollowNotifications")
	void resetFollowNotifications() {
		notificationService.resetFollowNotifications();
	}
}
