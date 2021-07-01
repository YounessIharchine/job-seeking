package com.pfa.jobseeking.rest.dto;

public class ApplicationWithoutCvDto {

	String coverLetter;
	
	public ApplicationWithoutCvDto() { }

	public ApplicationWithoutCvDto(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	
}
