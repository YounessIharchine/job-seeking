package com.pfa.jobseeking.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends User {

	
	public Admin() { }
	
	
	public Admin(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
