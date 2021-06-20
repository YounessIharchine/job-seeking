package com.pfa.jobseeking.rest.dto;

public class CompanyMandatoryInfoDto {

	String name;
	String city;
	String domain;
	String document;
	
	public CompanyMandatoryInfoDto() { }

	public CompanyMandatoryInfoDto(String name, String city, String domain, String document) {
		this.name = name;
		this.city = city;
		this.domain = domain;
		this.document = document;
	}

	public String getName() {
		return name;
	}
	public String getCity() {
		return city;
	}
	public String getDomain() {
		return domain;
	}
	public String getDocument() {
		return document;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	
}
