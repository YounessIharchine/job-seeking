package com.pfa.jobseeking.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("seeker")
public class Seeker extends User {

	
	public Seeker() { }
	
	
	
}
