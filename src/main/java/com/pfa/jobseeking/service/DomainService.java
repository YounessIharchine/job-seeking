package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.Domain;

public interface DomainService {

	Domain save(Domain domain);
	
	List<Domain> findAll();
		
	Domain findByName(String name);
	
}
