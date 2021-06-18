package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.CompanyCreationRequest;

public interface CompanyCreationRequestRepository extends CrudRepository<CompanyCreationRequest, Integer> {
	
	

}
