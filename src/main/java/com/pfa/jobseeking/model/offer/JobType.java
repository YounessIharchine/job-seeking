package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class JobType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobType")
	Set<JobOffer> jobOffers;
	
	
	public JobType() { }
	
	
	public JobType(String name) {
		super();
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<JobOffer> getJobOffers() {
		return jobOffers;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setJobOffers(Set<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}
}
