package com.pfa.jobseeking.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String date;
	
	
	
	public Request() { }


	
	public int getId() {
		return id;
	}
	public String getDate() {
		return date;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
