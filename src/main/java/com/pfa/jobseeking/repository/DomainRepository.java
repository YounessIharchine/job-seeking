package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.Domain;

public interface DomainRepository extends CrudRepository<Domain, Integer> {

	List<Domain> findAll();
	
	Domain findDomainByName(String name);
	
}
