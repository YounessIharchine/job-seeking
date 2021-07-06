package com.pfa.jobseeking.rest.response;

public class ApplicationNotificationResponse {

	int id;
	String offerTitle;
	int newApplications;
	
	
	
	public ApplicationNotificationResponse() { }

	
	
	public ApplicationNotificationResponse(int id, String offerTitle, int newApplications) {
		this.id = id;
		this.offerTitle = offerTitle;
		this.newApplications = newApplications;
	}

	
	
	public int getId() {
		return id;
	}
	public String getOfferTitle() {
		return offerTitle;
	}
	public int getNewApplications() {
		return newApplications;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}
	public void setNewApplications(int newApplications) {
		this.newApplications = newApplications;
	}
	
}
