package com.pfa.jobseeking.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.City;
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preferences_id")
	Preferences preferences;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cover_letter_id")
	CoverLetter coverLetter;
	
	@ManyToMany
	@JoinTable(name = "follow", joinColumns = @JoinColumn(name = "seeker_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
	Set<Company> companies;
	
	@ManyToMany
	@JoinTable(name = "save", joinColumns = @JoinColumn(name = "seeker_id"), inverseJoinColumns = @JoinColumn(name = "offer_id"))
	Set<Offer> offers;
	
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
	
	
	
}
