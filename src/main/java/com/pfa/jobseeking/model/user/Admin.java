package com.pfa.jobseeking.model.user;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.AdminNotification;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "admin_notif_id")
	AdminNotification adminNotification;
	
	public Admin() { }
	
	
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}


	public AdminNotification getAdminNotification() {
		return adminNotification;
	}


	public void setAdminNotification(AdminNotification adminNotification) {
		this.adminNotification = adminNotification;
	}
	
	
	
}
