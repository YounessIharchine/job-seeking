package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.JobType;

public interface JobTypeRepository extends CrudRepository <JobType, Integer>{

	List<JobType> findAll();

	JobType findJobTypeByName(String jobTypeName);

	void deleteJobTypeByName(String jobTypeName);
	
}
