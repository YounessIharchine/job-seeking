package com.pfa.jobseeking.rest.response;

public class SeekerResponse {

	int id;
	String name;
	String photo;
	String speciality;
	String city;
	
	
	
	public SeekerResponse() { }

	
	
	public SeekerResponse(int id, String name, String photo, String speciality, String city) {
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.speciality = speciality;
		this.city = city;
	}

	
	public int getId() {
		return id;
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

	
	
	public void setId(int id) {
		this.id = id;
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
