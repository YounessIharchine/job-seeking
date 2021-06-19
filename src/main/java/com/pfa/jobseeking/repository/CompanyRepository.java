package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

	Company findCompanyByName(String name);
	
}
