package com.pfa.jobseeking.rest.dto;

public class EducationDto {

	String type;
	String institution;
	String city;
	String startDate;
	String endDate;
	
	
	public EducationDto() { }


	public EducationDto(String type, String institution, String city, String startDate, String endDate) {
		this.type = type;
		this.institution = institution;
		this.city = city;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public String getType() {
		return type;
	}
	public String getInstitution() {
		return institution;
	}
	public String getCity() {
		return city;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}


	public void setType(String type) {
		this.type = type;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
}
