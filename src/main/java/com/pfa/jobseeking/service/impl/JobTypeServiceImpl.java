package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.repository.JobTypeRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.JobTypeService;

@Service
public class JobTypeServiceImpl implements JobTypeService {

	@Autowired
	JobTypeRepository jobTypeRepository;
	
	@Override
	public List<JobType> findAll() {
		return jobTypeRepository.findAll();
	}

	
	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void save(String jobTypeName) throws AlreadyExistsException {
		
		if(jobTypeRepository.findJobTypeByName(jobTypeName) != null)
			throw new AlreadyExistsException("This job type already exists in the database.");
		
		JobType jobType = new JobType(jobTypeName);
		
		jobTypeRepository.save(jobType);
		
	}

	
	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(String jobTypeName) throws NotFoundException {

		if(jobTypeRepository.findJobTypeByName(jobTypeName) == null)
			throw new NotFoundException("This job type doesn't exist in the database.");
		
		jobTypeRepository.deleteJobTypeByName(jobTypeName);
		
	}

}
