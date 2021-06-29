package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.AdminNotification;
import com.pfa.jobseeking.rest.response.CompanyNotificationResponse;
import com.pfa.jobseeking.rest.response.FollowNotificationResponse;

public interface NotificationService {
	
	//******************ADMIN NOTIFICATION******************
	
	AdminNotification getAdminNotifications();

	void resetAdminNotifications();
	
	
	//******************COMPANY NOTIFICATIONS******************
	
	CompanyNotificationResponse getCompanyNotifications();

	void resetCompanyNotifications();
	
	
	//******************FOLLOW NOTIFICATION******************
	
	List<FollowNotificationResponse> getFollowNotifications();

	void resetFollowNotifications();

}
