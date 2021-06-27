package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.AdminNotification;
import com.pfa.jobseeking.rest.response.CompanyNotificationResponse;
import com.pfa.jobseeking.rest.response.FollowNotificationResponse;

public interface NotificationService {
	
	AdminNotification getAdminNotifications();

	void resetAdminNotifications();
	
	CompanyNotificationResponse getCompanyNotifications();

	void resetCompanyNotifications();
	
	List<FollowNotificationResponse> getFollowNotifications();

	void resetFollowNotifications();

}
