package com.pfa.jobseeking.rest.response;

public class CompanyCreationRequestResponse {

	String date;
	String companyName;
	String encodedLogo;
	String encodedDocument;
	
	
	public CompanyCreationRequestResponse() {}

	public CompanyCreationRequestResponse(String date, String companyName, String encodedLogo, String encodedDocument) {
		super();
		this.date = date;
		this.companyName = companyName;
		this.encodedLogo = encodedLogo;
		this.encodedDocument = encodedDocument;
	}

	
	public String getDate() {
		return date;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getEncodedLogo() {
		return encodedLogo;
	}
	public String getEncodedDocument() {
		return encodedDocument;
	}

	
	
	public void setDate(String date) {
		this.date = date;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setEncodedLogo(String encodedLogo) {
		this.encodedLogo = encodedLogo;
	}
	public void setEncodedDocument(String encodedDocument) {
		this.encodedDocument = encodedDocument;
	}
	
	
	
}
