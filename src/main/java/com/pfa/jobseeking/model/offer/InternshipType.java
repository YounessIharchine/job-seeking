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
	
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "internshipType")
	Set<InternshipOffer> internshipOffers;
	
	
	public InternshipType() { }
	
	
	public InternshipType(String name) {
		super();
		this.name = name;
	}
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<InternshipOffer> getInternshipOffers() {
		return internshipOffers;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setInternshipOffers(Set<InternshipOffer> internshipOffers) {
		this.internshipOffers = internshipOffers;
	}
}
