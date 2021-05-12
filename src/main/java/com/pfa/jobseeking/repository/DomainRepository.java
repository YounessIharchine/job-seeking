package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.Domain;

public interface DomainRepository extends PagingAndSortingRepository<Domain, Integer> {

	List<Domain> findAll();
	
	Domain findDomainByName(String name);
	
}
