package com.pfa.jobseeking.rest.response;

public class CompanyResponse {

	int id;
	String name;
	String publicEmail;
	String phone;
	String city;
	String domain;
	String logo;
	String coverPhoto;
	String webSite;
	boolean isFollowed;
	
	public CompanyResponse() { }

	public CompanyResponse(int id, String name, String publicEmail, String phone, String city,
			String domain, String logo, String coverPhoto, String webSite, boolean isFollowed) {
		this.id = id;
		this.name = name;
		this.publicEmail = publicEmail;
		this.phone = phone;
		this.city = city;
		this.domain = domain;
		this.logo = logo;
		this.coverPhoto = coverPhoto;
		this.webSite = webSite;
		this.isFollowed = isFollowed;
	}

	
	public int getId() {
		return id;
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
	public String getLogo() {
		return logo;
	}
	public String getCoverPhoto() {
		return coverPhoto;
	}
	public String getWebSite() {
		return webSite;
	}	
	public boolean isFollowed() {
		return isFollowed;
	}

	
	
	public void setId(int id) {
		this.id = id;
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
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public void setFollowed(boolean isFollowed) {
		this.isFollowed = isFollowed;
	}
	
	
	
}
