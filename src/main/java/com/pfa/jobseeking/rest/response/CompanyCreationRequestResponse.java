package com.pfa.jobseeking.rest.response;

public class CompanyCreationRequestResponse {

	int id;
	String date;
	String companyName;
	
	
	public CompanyCreationRequestResponse() {}

	public CompanyCreationRequestResponse(int id, String date, String companyName) {
		this.id = id;
		this.date = date;
		this.companyName = companyName;
	}

	
	
	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	public String getCompanyName() {
		return companyName;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
