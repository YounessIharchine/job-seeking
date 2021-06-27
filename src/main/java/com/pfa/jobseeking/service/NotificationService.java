package com.pfa.jobseeking.service;

import com.pfa.jobseeking.model.AdminNotification;

public interface NotificationService {
	
	AdminNotification getAdminNotifications();

	void resetAdminNotifications();

	void resetCompanyNotifications();

	void resetFollowNotifications();

}
