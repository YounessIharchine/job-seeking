package com.pfa.jobseeking.rest.response;

public class SeekerResponse {
	
	String firstName;
	String lastName;
	String phone;
	String address;
	String birthDate;
	String city;
	String cv;
	String photo;
	String speciality;
	String description;
	String portefolio;
	String github;
	
	public SeekerResponse() { }

	public SeekerResponse(String firstName, String lastName, String phone, String address, String birthDate,
			String city, String cv, String photo, String speciality, String description, String portefolio,
			String github) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.birthDate = birthDate;
		this.city = city;
		this.cv = cv;
		this.photo = photo;
		this.speciality = speciality;
		this.description = description;
		this.portefolio = portefolio;
		this.github = github;
	}

	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getCity() {
		return city;
	}
	public String getCv() {
		return cv;
	}
	public String getPhoto() {
		return photo;
	}
	public String getSpeciality() {
		return speciality;
	}
	public String getDescription() {
		return description;
	}
	public String getPortefolio() {
		return portefolio;
	}
	public String getGithub() {
		return github;
	}
	
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPortefolio(String portefolio) {
		this.portefolio = portefolio;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	
	
}
