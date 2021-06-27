package com.pfa.jobseeking.rest.response;

public class ApplicationNotificationResponse {

	String offerTitle;
	int newApplications;
	
	
	
	public ApplicationNotificationResponse() { }

	
	
	public ApplicationNotificationResponse(String offerTitle, int newApplications) {
		this.offerTitle = offerTitle;
		this.newApplications = newApplications;
	}

	
	
	public String getOfferTitle() {
		return offerTitle;
	}
	public int getNewApplications() {
		return newApplications;
	}

	
	
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	public void setNewApplications(int newApplications) {
		this.newApplications = newApplications;
	}
	
}
