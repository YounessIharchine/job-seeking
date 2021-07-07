package com.pfa.jobseeking.rest.dto;

public class CompanyDto {

	String email;
	String password;
	String name;
	String publicEmail;
	String phone;
	String city;
	String domain;
	String document;
	String logo;
	
	
	
	public CompanyDto() { }

	
	
	public CompanyDto(String email, String password, String name, String publicEmail, String phone, String city,
			String domain, String document, String logo) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.publicEmail = publicEmail;
		this.phone = phone;
		this.city = city;
		this.domain = domain;
		this.document = document;
		this.logo = logo;
	}

	
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getPublicEmail() {
		return publicEmail;
	}
	public String getPhone() {
		return phone;
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
	public String getLogo() {
		return logo;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
