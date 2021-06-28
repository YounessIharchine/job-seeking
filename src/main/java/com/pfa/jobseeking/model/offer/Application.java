package com.pfa.jobseeking.model.offer;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class Application {

	@EmbeddedId
	ApplicationKey id = new ApplicationKey()
	;
	
	@ManyToOne
	@MapsId("seekerId")
	@JoinColumn(name = "seeker_id")
	Seeker seeker;
	
	@ManyToOne
	@MapsId("offerId")
	@JoinColumn(name = "offer_id")
	Offer offer;
	
	String date;
	String cv;
	String coverLetter;
	
	
	
	public Application() { }


	
	public ApplicationKey getId() {
		return id;
	}
	public Seeker getSeeker() {
		return seeker;
	}
	public Offer getOffer() {
		return offer;
	}
	public String getDate() {
		return date;
	}
	public String getCv() {
		return cv;
	}
	public String getCoverLetter() {
		return coverLetter;
	}

	

	public void setId(ApplicationKey id) {
		this.id = id;
	}
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	
	
	
}
