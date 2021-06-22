package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface DomainService {

	List<Domain> findAll();
		
	List<Domain> save(NameDto domain) throws AlreadyExistsException;

	List<Domain> delete(String domain) throws NotFoundException;
	
	
	//for filling database
	Domain save(Domain domain);
	Domain findByName(String name);

}
