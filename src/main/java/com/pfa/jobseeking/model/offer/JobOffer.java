package com.pfa.jobseeking.model.offer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("job")
public class JobOffer extends Offer {

	@ManyToOne
	@JoinColumn(name = "internship_type__id")
	JobType jobType;
	
	
	public JobOffer() { }
}
