package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Technology;

public interface TechnologyRepository extends CrudRepository<Technology, Integer> {
	
	Technology findById(int id);

}
