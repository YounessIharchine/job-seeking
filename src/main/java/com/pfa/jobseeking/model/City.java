package com.pfa.jobseeking.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@OneToMany(mappedBy = "city")
	Set<Seeker> seekers;
	@OneToMany(mappedBy = "city")
	Set<Company> companies;
	@OneToMany(mappedBy = "city")
	Set<Offer> offers;
	
	public City() { }
}
