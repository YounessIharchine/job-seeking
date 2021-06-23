package com.pfa.jobseeking.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Company;

@Entity
public class CompanyNotification extends Notification {

	@OneToOne(mappedBy = "companyNotification")
	Company company;
	
	int newFollowers;
	
	
	public CompanyNotification() { }

	
	public Company getCompany() {
		return company;
	}
	public int getNewFollowers() {
		return newFollowers;
	}

	
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setNewFollowers(int newFollowers) {
		this.newFollowers = newFollowers;
	}
	
	
	public void incrementNewFollowers() {
		this.newFollowers++;
	}
	public void resetNewFollowers() {
		this.newFollowers = 0;
	}
}
