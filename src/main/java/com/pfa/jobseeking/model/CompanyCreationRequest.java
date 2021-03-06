package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Company;

@Entity
public class CompanyCreationRequest extends Request {

	@OneToOne
	@JoinColumn(name = "company_id")
	Company company;
	
	
	public CompanyCreationRequest() { }


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}
	
	
}
