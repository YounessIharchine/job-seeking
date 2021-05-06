package com.pfa.jobseeking.model.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TimePeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String startDate;
	String endDate;
	
	
	public TimePeriod() { }
	
	
}
