package com.pfa.jobseeking.rest.response;

public class OfferResponse {

	int id;
	String title;
	String description;
	String date;
	String city;
	String domain;
	String companyName;
	boolean isInternshipOffer;
	String type;
	String duration;
	boolean isSaved;
	
	public OfferResponse() { }

	public OfferResponse(int id, String title, String description, String date, String city, String domain,
			String companyName, boolean isInternshipOffer, String type, String duration, boolean isSaved) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.city = city;
		this.domain = domain;
		this.companyName = companyName;
		this.isInternshipOffer = isInternshipOffer;
		this.type = type;
		this.duration = duration;
		this.isSaved = isSaved;
	}
	
	
	public int getId() {
		return id;
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
	public boolean isInternshipOffer() {
		return isInternshipOffer;
	}
	public String getType() {
		return type;
	}
	public String getDuration() {
		return duration;
	}
	public boolean isSaved() {
		return isSaved;
	}

	
	public void setId(int id) {
		this.id = id;
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
	public void setInternshipOffer(boolean isInternshipOffer) {
		this.isInternshipOffer = isInternshipOffer;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	
	
	
	
}
