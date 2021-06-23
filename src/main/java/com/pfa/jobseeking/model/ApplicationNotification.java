package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.offer.Offer;

@Entity
public class ApplicationNotification extends Notification {

	@OneToOne(mappedBy = "applicationNotification")
	Offer offer;
	
	int newApplications;
	
	
	public ApplicationNotification() { }


	public Offer getOffer() {
		return offer;
	}
	public int getNewApplications() {
		return newApplications;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public void setNewApplications(int newApplications) {
		this.newApplications = newApplications;
	}
	
	
	public void incrementNewApplications() {
		this.newApplications++;
	}
	public void resetNewApplications() {
		this.newApplications = 0;
	}
	
}
