package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class InternshipType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String type;
	
	@JsonIgnore
	@OneToMany(mappedBy = "internshipType")
	Set<InternshipOffer> internshipOffers;
	
	
	public InternshipType() { }
	
	
	public InternshipType(int id, String type, Set<InternshipOffer> internshipOffers) {
		super();
		this.id = id;
		this.type = type;
		this.internshipOffers = internshipOffers;
	}
	
	
	public int getId() {
		return id;
	}
	public String getType() {
		return type;
	}
	public Set<InternshipOffer> getInternshipOffers() {
		return internshipOffers;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setInternshipOffers(Set<InternshipOffer> internshipOffers) {
		this.internshipOffers = internshipOffers;
	}
}
