package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.repository.JobTypeRepository;
import com.pfa.jobseeking.service.JobTypeService;

@Service
public class JobTypeServiceImpl implements JobTypeService {

	@Autowired
	JobTypeRepository jobTypeRepository;
	
	@Override
	public List<JobType> findAll() {
		return jobTypeRepository.findAll();
	}

}
