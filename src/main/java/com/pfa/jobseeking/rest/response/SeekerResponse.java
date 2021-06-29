package com.pfa.jobseeking.rest.response;

public class SeekerResponse {

	String name;
	String photo;
	String speciality;
	String city;
	
	
	
	public SeekerResponse() { }

	
	
	public SeekerResponse(String name, String photo, String speciality, String city) {
		this.name = name;
		this.photo = photo;
		this.speciality = speciality;
		this.city = city;
	}

	
	
	public String getName() {
		return name;
	}
	public String getPhoto() {
		return photo;
	}
	public String getSpeciality() {
		return speciality;
	}
	public String getCity() {
		return city;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
