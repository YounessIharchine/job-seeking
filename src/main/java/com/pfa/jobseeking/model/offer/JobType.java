package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class JobType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String type;
	
	@OneToMany(mappedBy = "jobType")
	Set<JobOffer> jobOffers;
	
	
	public JobType() { }
}
