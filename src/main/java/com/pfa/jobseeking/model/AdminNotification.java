package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.user.Admin;

@Entity
public class AdminNotification extends Notification {

	@JsonIgnore
	@OneToOne(mappedBy = "adminNotification")
	Admin admin;
	
	int newCompanyCreationRequests;
	int newOfferCreationRequests;
	
	
	public AdminNotification() { }

	
	public int getNewCompanyCreationRequests() {
		return newCompanyCreationRequests;
	}
	public int getNewOfferCreationRequests() {
		return newOfferCreationRequests;
	}
	public Admin getAdmin() {
		return admin;
	}


	
	public void setNewCompanyCreationRequests(int newCompanyCreationRequests) {
		this.newCompanyCreationRequests = newCompanyCreationRequests;
	}
	public void setNewOfferCreationRequests(int newOfferCreationRequests) {
		this.newOfferCreationRequests = newOfferCreationRequests;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
	public void incrementNewCompanyCreationRequests() {
		this.newCompanyCreationRequests++;
	}
	public void incrementNewOfferCreationRequests() {
		this.newOfferCreationRequests++;
	}
	public void resetNewCompanyCreationRequests() {
		this.newCompanyCreationRequests = 0;
	}
	public void resetNewOfferCreationRequests() {
		this.newOfferCreationRequests = 0;
	}
	
}
