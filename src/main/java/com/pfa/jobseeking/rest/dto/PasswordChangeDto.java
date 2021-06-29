package com.pfa.jobseeking.rest.dto;

public class PasswordChangeDto {
	
	private String email;
	private String password;
	private String code;
	
	
	public PasswordChangeDto() { }
	
	
	public PasswordChangeDto(String email, String password, String code) {
		this.email = email;
		this.password = password;
		this.code = code;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
