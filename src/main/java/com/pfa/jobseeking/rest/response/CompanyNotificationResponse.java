package com.pfa.jobseeking.rest.response;

import java.util.ArrayList;
import java.util.List;

public class CompanyNotificationResponse {

	int newFollowers;
	List<ApplicationNotificationResponse> applicationNotifications = new ArrayList<>();
	
	
	
	public CompanyNotificationResponse() { }

	
	
	public CompanyNotificationResponse(int newFollowers,
			List<ApplicationNotificationResponse> applicationNotifications) {
		this.newFollowers = newFollowers;
		this.applicationNotifications = applicationNotifications;
	}

	
	
	public int getNewFollowers() {
		return newFollowers;
	}
	public List<ApplicationNotificationResponse> getApplicationNotifications() {
		return applicationNotifications;
	}

	
	
	public void setNewFollowers(int newFollowers) {
		this.newFollowers = newFollowers;
	}
	public void setApplicationNotifications(List<ApplicationNotificationResponse> applicationNotifications) {
		this.applicationNotifications = applicationNotifications;
	}
	
	
	
	public void addApplicationNotification(ApplicationNotificationResponse applicationNotificationResponse) {
		this.applicationNotifications.add(applicationNotificationResponse);
	}
}
