package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.InternshipType;

public interface InternshipTypeRepository extends CrudRepository<InternshipType, Integer> {

	List<InternshipType> findAll(); 
}
