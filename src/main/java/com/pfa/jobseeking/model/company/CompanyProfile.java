package com.pfa.jobseeking.model.company;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Company;

@Entity
public class CompanyProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToOne(mappedBy = "companyProfile")
	Company company;
	
	String logo;
	String coverPhoto;
	String webSite;
	
	@OneToMany(mappedBy = "companyProfile")
	Set<Photo> photos;
	
	@OneToMany(mappedBy = "companyProfile")
	Set<Paragraph> paragraphs;
	
	
	
	public CompanyProfile() { }
}
