package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.seeker.Follow;

@Entity
public class FollowNotification extends Notification {

	
	@OneToOne(mappedBy = "followNotification")
	Follow follow;
	
	int newOffers;
	
	
	
	public FollowNotification() { }

	
	
	public Follow getFollow() {
		return follow;
	}
	public int getNewOffers() {
		return newOffers;
	}

	
	
	public void setFollow(Follow follow) {
		this.follow = follow;
	}
	public void setNewOffers(int newOffers) {
		this.newOffers = newOffers;
	}
	
	
	
	public void incrementNewOffers() {
		this.newOffers++;
	}
	public void resetNewOffers() {
		this.newOffers = 0;
	}
}
