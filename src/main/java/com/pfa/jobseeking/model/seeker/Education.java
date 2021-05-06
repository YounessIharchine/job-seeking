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
public class Education {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String type;
	String institution;
	String city;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "time_period_id")
	TimePeriod timePeriod;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	
	public Education() { }

	

	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getInstitution() {
		return institution;
	}
	public String getCity() {
		return city;
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
	public void setType(String type) {
		this.type = type;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	
}
