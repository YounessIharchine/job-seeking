package com.pfa.jobseeking.model.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.CompanyCreationRequest;
import com.pfa.jobseeking.model.CompanyNotification;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.company.CompanyProfile;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.Follow;

@Entity
@DiscriminatorValue("company")
public class Company extends User {

	String name;
	String publicEmail;
	String phone;
	String documentPath;
	@JsonIgnore //if you want to disable this, remove it from the getter
	boolean isVerified;
	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;
	@ManyToOne
	@JoinColumn(name = "domain_id")
	Domain domain;
	
	@JsonIgnore
	@OneToOne(mappedBy = "company", cascade = CascadeType.ALL)
	CompanyCreationRequest companyCreationRequest;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_profile_id")
	CompanyProfile companyProfile;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	Set<Offer> offers = new HashSet<Offer>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "company", orphanRemoval = true, cascade = CascadeType.REMOVE)
	List<Follow> follows = new ArrayList<Follow>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "company_notif_id")
	CompanyNotification companyNotification;
	
	public Company() { }
	
	
	public Company(String email, String password) {
		this.email = email;
		this.password = password;
	}


	public String getName() {
		return name;
	}
	public String getPublicEmail() {
		return publicEmail;
	}
	public String getPhone() {
		return phone;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	@JsonIgnore
	public boolean isVerified() {
		return isVerified;
	}
	public City getCity() {
		return city;
	}
	public Domain getDomain() {
		return domain;
	}
	public CompanyCreationRequest getCompanyCreationRequest() {
		return companyCreationRequest;
	}
	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public CompanyNotification getCompanyNotification() {
		return companyNotification;
	}	
	public List<Follow> getFollows() {
		return follows;
	}


	
	public void setName(String name) {
		this.name = name;
	}
	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public void setCompanyCreationRequest(CompanyCreationRequest companyCreationRequest) {
		this.companyCreationRequest = companyCreationRequest;
	}
	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public void setCompanyNotification(CompanyNotification companyNotification) {
		this.companyNotification = companyNotification;
	}
	public void setFollows(List<Follow> follows) {
		this.follows = follows;
	}
	
	
	public void addOffer(Offer offer) {
		this.offers.add(offer);
	}








	
	
	
}
