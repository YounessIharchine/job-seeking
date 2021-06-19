package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface JobTypeService {

	List<JobType> findAll();

	void save(NameDto jobType) throws AlreadyExistsException;

	void delete(String jobType) throws NotFoundException;

}
