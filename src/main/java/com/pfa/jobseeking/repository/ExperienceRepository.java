package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Experience;

public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

	Experience findById(int id);
	
}
