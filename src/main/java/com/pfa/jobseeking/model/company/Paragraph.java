package com.pfa.jobseeking.model.company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Paragraph {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String title;
	String text;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "company_profile_id")
	CompanyProfile companyProfile;
	
	
	public Paragraph() { }

	public Paragraph(String text) {
		this.text = text;
	}

	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getText() {
		return text;
	}
	public CompanyProfile getCompanyProfile() {
		return companyProfile;
	}

	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setCompanyProfile(CompanyProfile companyProfile) {
		this.companyProfile = companyProfile;
	}


	
}
