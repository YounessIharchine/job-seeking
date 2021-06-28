package com.pfa.jobseeking.rest.dto;

public class ApplicationDto {

	String cv;
	String coverLetter;
	
	
	
	public ApplicationDto() { }

	
	
	public ApplicationDto(String cv, String coverLetter) {
		this.cv = cv;
		this.coverLetter = coverLetter;
	}

	
	
	public String getCv() {
		return cv;
	}
	public String getCoverLetter() {
		return coverLetter;
	}

	
	
	public void setCv(String cv) {
		this.cv = cv;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
}
