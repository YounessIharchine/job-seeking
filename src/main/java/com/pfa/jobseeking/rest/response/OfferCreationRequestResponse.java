package com.pfa.jobseeking.rest.response;

public class OfferCreationRequestResponse {

	String requestDate;
	String title;
	String description;
	String offerDate;
	
	public OfferCreationRequestResponse() { }

	public OfferCreationRequestResponse(String requestDate, String title, String description, String offerDate) {
		super();
		this.requestDate = requestDate;
		this.title = title;
		this.description = description;
		this.offerDate = offerDate;
	}

	
	public String getRequestDate() {
		return requestDate;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getOfferDate() {
		return offerDate;
	}

	
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOfferDate(String offerDate) {
		this.offerDate = offerDate;
	}
	
	
}
