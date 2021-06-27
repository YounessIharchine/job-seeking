package com.pfa.jobseeking.rest.dto;

public class OfferDto {

	String title;
	String description;
	String date;
	String city;
	String domain;
	String type;
	String duration;
	
	
	public OfferDto() { }
	
	
	public OfferDto(String title, String description, String date, String city, String domain, String type,
			String duration) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.city = city;
		this.domain = domain;
		this.type = type;
		this.duration = duration;
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
	public String getType() {
		return type;
	}
	public String getDuration() {
		return duration;
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
	public void setType(String type) {
		this.type = type;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
}
