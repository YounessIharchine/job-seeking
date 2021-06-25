package com.pfa.jobseeking.rest.response;

public class UserResponse {
 
	int id;
	String role;
	
	
	
	public UserResponse() { }

	
	
	public UserResponse(int id, String role) {
		this.id = id;
		this.role = role;
	}

	
	
	public int getId() {
		return id;
	}
	public String getRole() {
		return role;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
