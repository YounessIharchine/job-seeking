package com.pfa.jobseeking.rest.response;

public class CompanyPhotoResponse {

	int id;
	String photo;
	
	
	
	public CompanyPhotoResponse() { }
	
	public CompanyPhotoResponse(int id, String photo) {
		this.id = id;
		this.photo = photo;
	}

	
	
	public int getId() {
		return id;
	}
	public String getPhoto() {
		return photo;
	}
	
	

	public void setId(int id) {
		this.id = id;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
