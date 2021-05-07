package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Admin;

@Entity
public class AdminNotification extends Notification {

	@OneToOne(mappedBy = "adminNotification")
	Admin admin;
	
	int newRequests;
	
	
	public AdminNotification() { }


	public int getNewRequests() {
		return newRequests;
	}
	public Admin getAdmin() {
		return admin;
	}


	
	public void setNewRequests(int newRequests) {
		this.newRequests = newRequests;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	
	
	
}
