package com.pfa.jobseeking.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.SeekerNotification;
import com.pfa.jobseeking.model.offer.Application;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.CoverLetter;
import com.pfa.jobseeking.model.seeker.Preferences;
import com.pfa.jobseeking.model.seeker.Profile;

@Entity
@DiscriminatorValue("seeker")
public class Seeker extends User {

	
	String firstName;
	String lastName;
	String phone;
	String address;
	String birthDate;
	@ManyToOne
	@JoinColumn(name = "city_id")
	City city;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preferences_id")
	Preferences preferences;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cover_letter_id")
	CoverLetter coverLetter;
	
	@JsonIgnore
	@OneToMany(mappedBy = "seeker", orphanRemoval = true, cascade = CascadeType.REMOVE)
	Set<Application> applications;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "seeker_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	Set<Company> companies;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "save", joinColumns = @JoinColumn(name = "seeker_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
	Set<Offer> offers = new HashSet<>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "seeker_notif_id")
	SeekerNotification seekerNotification;
	
	
	public Seeker() { }
	
	
	
	public Seeker(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public CoverLetter getCoverLetter() {
		return coverLetter;
	}
	public City getCity() {
		return city;
	}
	public Profile getProfile() {
		return profile;
	}
	public Preferences getPreferences() {
		return preferences;
	}
	public Set<Company> getCompanies() {
		return companies;
	}
	public Set<Offer> getOffers() {
		return offers;
	}
	public SeekerNotification getSeekerNotification() {
		return seekerNotification;
	}
	
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setCoverLetter(CoverLetter coverLetter) {
		this.coverLetter = coverLetter;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}
	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	public void setSeekerNotification(SeekerNotification seekerNotification) {
		this.seekerNotification = seekerNotification;
	}
	
	
	public void saveOffer(Offer offer) {
		this.offers.add(offer);
	}
	public void followCompany(Company company) {
		this.companies.add(company);
	}
	
	public void unsaveOffer(Offer offer) {
		this.offers.remove(offer);
	}
	public void unfollowCompany(Company company) {
		this.companies.remove(company);
	}
	
}
