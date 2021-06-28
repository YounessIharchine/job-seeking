package com.pfa.jobseeking.rest.response;

public class CompanyCreationRequestResponse {

	String date;
	String companyName;
	
	
	public CompanyCreationRequestResponse() {}

	public CompanyCreationRequestResponse(String date, String companyName) {
		super();
		this.date = date;
		this.companyName = companyName;
	}

	
	public String getDate() {
		return date;
	}
	public String getCompanyName() {
		return companyName;
	}

	
	
	public void setDate(String date) {
		this.date = date;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
}
