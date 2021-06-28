package com.pfa.jobseeking.rest.dto;

public class SeekerDto {

	String email;
	String firstName;
	String lastName;
	String phone;
	String address;
	String birthDate;
	String city;
	String speciality;
	String github;
	String portefolio;
	
	
	
	public SeekerDto() { }

	
	
	public SeekerDto(String email, String firstName, String lastName, String phone, String address, String birthDate, String city, 
			String speciality, String github, String portefolio) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.birthDate = birthDate;
		this.city = city;
		this.speciality = speciality;
		this.github = github;
		this.portefolio = portefolio;
	}

	
	public String getEmail() {
		return email;
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
	public String getSpeciality() {
		return speciality;
	}
	public String getGithub() {
		return github;
	}
	public String getPortefolio() {
		return portefolio;
	}

	
	
	public void setEmail(String email) {
		this.email = email;
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
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public void setPortefolio(String portefolio) {
		this.portefolio = portefolio;
	}

	
	
	
}
