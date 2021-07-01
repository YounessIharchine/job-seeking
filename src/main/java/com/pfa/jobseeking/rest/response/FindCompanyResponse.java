package com.pfa.jobseeking.rest.response;

public class FindCompanyResponse {

	int id;
	String name;
	String city;
	String domain;
	String logo;

	
	
	public FindCompanyResponse() { }

	
	
	public FindCompanyResponse(int id, String name, String city, String domain, String logo) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.domain = domain;
		this.logo = logo;
	}


	public int getId() {
		return id;
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
	public String getLogo() {
		return logo;
	}
	
	
	public void setId(int id) {
		this.id = id;
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
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
}
