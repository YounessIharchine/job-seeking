package com.pfa.jobseeking.rest.response;

public class FollowNotificationResponse {

	String companyName;
	int newOffers;
	
	
	
	public FollowNotificationResponse() { }

	
	
	public FollowNotificationResponse(String companyName, int newOffers) {
		this.companyName = companyName;
		this.newOffers = newOffers;
	}

	
	
	public String getCompanyName() {
		return companyName;
	}
	public int getNewOffers() {
		return newOffers;
	}

	
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setNewOffers(int newOffers) {
		this.newOffers = newOffers;
	}
	
}
