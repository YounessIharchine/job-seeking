package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String title;
	String description;
	String date;
	boolean isOpen;
	boolean isVerified;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	Company company;
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;
	@ManyToOne
	@JoinColumn(name = "domain_id")
	Domain domain;
	
	@ManyToMany(mappedBy = "offers")
	Set<Seeker> seekers;
	
	
	public Offer() { }
	
	
	
}
