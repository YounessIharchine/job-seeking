package com.pfa.jobseeking.model.seeker;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String title;
	String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "time_period_id")
	TimePeriod timePeriod;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	
	public Project() { }
	
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
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
	public void setTitle(String title) {
		this.title = title;
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
