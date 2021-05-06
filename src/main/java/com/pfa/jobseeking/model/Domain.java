package com.pfa.jobseeking.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Company;

@Entity
public class Domain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	
	@OneToMany(mappedBy = "domain")
	Set<Company> companies;
	@OneToMany(mappedBy = "domain")
	Set<Offer> offers;
	
	
	public Domain() { }
}
