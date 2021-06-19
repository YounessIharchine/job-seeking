package com.pfa.jobseeking.rest.response;

public class OfferResponse {

	String title;
	String description;
	String date;
	String city;
	String domain;
	String companyName;
	
	public OfferResponse() { }

	public OfferResponse(String title, String description, String date, String city, String domain,
			String companyName) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.city = city;
		this.domain = domain;
		this.companyName = companyName;
	}
	
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getDate() {
		return date;
	}
	public String getCity() {
		return city;
	}
	public String getDomain() {
		return domain;
	}
	public String getCompanyName() {
		return companyName;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
