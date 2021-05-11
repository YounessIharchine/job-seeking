package com.pfa.jobseeking.model.offer;

import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.ApplicationNotification;
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
	
	@OneToMany(mappedBy = "offer", orphanRemoval = true, cascade = CascadeType.REMOVE)
	Set<Application> applications;
	
	@ManyToMany(mappedBy = "offers")
	Set<Seeker> seekers;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "application_notif_id")
	ApplicationNotification applicationNotification;
	
	
	
	public Offer() { }
	
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getDate() {
		return date;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public Company getCompany() {
		return company;
	}
	public City getCity() {
		return city;
	}
	public Domain getDomain() {
		return domain;
	}
	public Set<Seeker> getSeekers() {
		return seekers;
	}
	public ApplicationNotification getApplicationNotification() {
		return applicationNotification;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	public void setSeekers(Set<Seeker> seekers) {
		this.seekers = seekers;
	}
	public void setApplicationNotification(ApplicationNotification applicationNotification) {
		this.applicationNotification = applicationNotification;
	}
	
	
	
}
