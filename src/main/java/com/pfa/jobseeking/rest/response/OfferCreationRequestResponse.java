package com.pfa.jobseeking.rest.response;

public class OfferCreationRequestResponse {

	int id;
	String requestDate;
	String title;
	String description;
	
	public OfferCreationRequestResponse() { }

	public OfferCreationRequestResponse(int id, String requestDate, String title, String description) {
		this.id = id;
		this.requestDate = requestDate;
		this.title = title;
		this.description = description;
	}

	
	public int getId() {
		return id;
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
	

	public void setId(int id) {
		this.id = id;
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
	
	
}
