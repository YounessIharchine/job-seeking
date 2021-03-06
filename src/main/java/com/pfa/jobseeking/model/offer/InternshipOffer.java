package com.pfa.jobseeking.model.offer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("internship")
public class InternshipOffer extends Offer {

	@ManyToOne
	@JoinColumn(name = "duration_id")
	Duration duration;
	
	@ManyToOne
	@JoinColumn(name = "internship_type_id")
	InternshipType internshipType;
	
	
	public InternshipOffer() { }


	public Duration getDuration() {
		return duration;
	}
	public InternshipType getInternshipType() {
		return internshipType;
	}


	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public void setInternshipType(InternshipType internshipType) {
		this.internshipType = internshipType;
	}
	
	
}
