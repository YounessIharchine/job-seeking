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
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	public Experience() { }
	
	
	
}
