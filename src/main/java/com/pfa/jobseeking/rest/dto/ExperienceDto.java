package com.pfa.jobseeking.rest.dto;

public class ExperienceDto {

	String jobTitle;
	String company;
	String city;
	String description;
	String startDate;
	String endDate;
	
	
	public ExperienceDto() { }


	public ExperienceDto(String jobTitle, String company, String city, String description, String startDate,
			String endDate) {
		this.jobTitle = jobTitle;
		this.company = company;
		this.city = city;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public String getJobTitle() {
		return jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public String getCity() {
		return city;
	}
	public String getDescription() {
		return description;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
