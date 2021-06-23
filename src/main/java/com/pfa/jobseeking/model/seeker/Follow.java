package com.pfa.jobseeking.model.seeker;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pfa.jobseeking.model.FollowNotification;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class Follow {

	@EmbeddedId
	FollowKey id;
	
	@ManyToOne
	@MapsId("seekerId")
	@JoinColumn(name = "seeker_id")
	Seeker seeker;
	
	@ManyToOne
	@MapsId("companyId")
	@JoinColumn(name = "company_id")
	Company company;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "follow_notif_id")
	FollowNotification followNotification;
	
	
	
	public Follow() { }


	
	public FollowKey getId() {
		return id;
	}
	public Seeker getSeeker() {
		return seeker;
	}
	public Company getCompany() {
		return company;
	}
	public FollowNotification getFollowNotification() {
		return followNotification;
	}


	
	public void setId(FollowKey id) {
		this.id = id;
	}
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setFollowNotification(FollowNotification followNotification) {
		this.followNotification = followNotification;
	}
	
	
	
}
