package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class SeekerNotification extends Notification {

	@OneToOne(mappedBy = "seekerNotification")
	Seeker seeker;
	
	int newOffers;
	
	
	public SeekerNotification() { }


	public Seeker getSeeker() {
		return seeker;
	}
	public int getNewOffers() {
		return newOffers;
	}


	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	public void setNewOffers(int newOffers) {
		this.newOffers = newOffers;
	}
	
	
	
	
}
