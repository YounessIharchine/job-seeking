package com.pfa.jobseeking.model.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TimePeriod {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String startDate;
	String endDate;
	
	
	public TimePeriod() { }


	public TimePeriod(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getId() {
		return id;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
