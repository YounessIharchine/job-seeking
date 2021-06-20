package com.pfa.jobseeking.model.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String photoPath;
	
	@ManyToOne
	@JoinColumn(name = "company_profile_id")
	CompanyProfile companyProfile;
	
	
	public Photo() { }


	public Photo(String photoPath) {
		this.photoPath = photoPath;
	}


	public int getId() {
		return id;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}


	public void setId(int id) {
		this.id = id;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}
	
}
