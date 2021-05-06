package com.pfa.jobseeking.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.CompanyCreationRequest;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.company.CompanyProfile;
import com.pfa.jobseeking.model.offer.Offer;

@Entity
@DiscriminatorValue("company")
public class Company extends User {

	String name;
	String publicEmail;
	String phone;
	boolean isVerified;
	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;
	@ManyToOne
	@JoinColumn(name = "domain_id")
	Domain domain;
	
	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
	CompanyCreationRequest companyCreationRequest;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_profile_id")
	CompanyProfile companyProfile;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	Set<Offer> offers;
	
	@ManyToMany(mappedBy = "companies")
	Set<Seeker> seekers;
	
	
	
	public Company() { }
	
	
	public Company(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
