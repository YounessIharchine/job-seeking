package com.pfa.jobseeking.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("company")
public class Company extends User {

	
	public Company() { }
	
	
}
