package com.pfa.jobseeking.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pfa.jobseeking.model.Domain;

public interface DomainService {

	Domain save(Domain domain);
	
	List<Domain> findAll();
	
	List<Domain> findAll(Pageable pageable);
	
	Domain findByName(String name);
	
}
