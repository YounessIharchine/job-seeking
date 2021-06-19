package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Duration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String duration;
	
	@OneToMany(mappedBy = "duration")
	Set<InternshipOffer> internshipOffers;
	
	
	public Duration() { }


	public Duration(String duration) {
		this.duration = duration;
	}


	public int getId() {
		return id;
	}
	public String getDuration() {
		return duration;
	}
	public Set<InternshipOffer> getInternshipOffers() {
		return internshipOffers;
	}


	public void setId(int id) {
		this.id = id;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setInternshipOffers(Set<InternshipOffer> internshipOffers) {
		this.internshipOffers = internshipOffers;
	}
	
	
}
