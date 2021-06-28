package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.CompanyCreationRequest;

public interface CompanyCreationRequestRepository extends CrudRepository<CompanyCreationRequest, Integer> {
	
	List<CompanyCreationRequest> findAll();
	
	CompanyCreationRequest findById(int id);
	
	CompanyCreationRequest findByCompanyName(String companyName);

}

