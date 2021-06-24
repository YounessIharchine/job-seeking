package com.pfa.jobseeking.model.seeker;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Experience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String jobTitle;
	String company;
	String city;
	String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "time_period_id")
	TimePeriod timePeriod;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	public Experience() { }


	public int getId() {
		return id;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public String getCity() {
		return city;
	}
	public String getDescription() {
		return description;
	}
	public TimePeriod getTimePeriod() {
		return timePeriod;
	}
	public Profile getProfile() {
		return profile;
	}


	public void setId(int id) {
		this.id = id;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	
}
