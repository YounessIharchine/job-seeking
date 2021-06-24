package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Education;

public interface EducationRepository extends CrudRepository<Education, Integer> {
	
	Education findById(int id);

}
