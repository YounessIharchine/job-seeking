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
	
	String type;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobType")
	Set<JobOffer> jobOffers;
	
	
	public JobType() { }
	
	
	public JobType(int id, String type, Set<JobOffer> jobOffers) {
		super();
		this.id = id;
		this.type = type;
		this.jobOffers = jobOffers;
	}
	
	
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Set<JobOffer> getJobOffers() {
		return jobOffers;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setJobOffers(Set<JobOffer> jobOffers) {
		this.jobOffers = jobOffers;
	}
}
