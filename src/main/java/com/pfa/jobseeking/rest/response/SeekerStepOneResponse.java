package com.pfa.jobseeking.rest.response;

public class SeekerStepOneResponse {
	
	String firstName;
	String lastName;
	String phone;
	String address;
	String birthDate;
	String city;
	
public SeekerStepOneResponse() { }
	
	

	public SeekerStepOneResponse(String firstName, String lastName, String phone, String address,
			String birthDate, String city) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.birthDate = birthDate;
		this.city = city;
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

}
