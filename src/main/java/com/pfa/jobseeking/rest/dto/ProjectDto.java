package com.pfa.jobseeking.rest.dto;

public class ProjectDto {

	String title;
	String description;
	String startDate;
	String endDate;
	
	
	public ProjectDto() { }

	
	public ProjectDto(String title, String description, String startDate, String endDate) {
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	

	public String getTitle() {
		return title;
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


	public void setTitle(String title) {
		this.title = title;
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
