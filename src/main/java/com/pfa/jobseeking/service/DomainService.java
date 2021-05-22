package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface DomainService {

	Domain save(Domain domain);
	
	List<Domain> findAll();
		
	Domain findByName(String name);

	void save(String domain) throws AlreadyExistsException;

	void delete(String domain) throws NotFoundException;
	
}
