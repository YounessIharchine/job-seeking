package com.pfa.jobseeking.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Company;

@Entity
public class Domain {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "domain")
	Set<Company> companies;
	@JsonIgnore
	@OneToMany(mappedBy = "domain")
	Set<Offer> offers;
	
	
	
	public Domain() { }
	
	
	
	public Domain(String name) {
		this.name = name;
	}



	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<Company> getCompanies() {
		return companies;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
}
