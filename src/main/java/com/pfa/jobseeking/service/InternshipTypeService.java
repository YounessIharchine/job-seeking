package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface InternshipTypeService {

	List<InternshipType> findAll();

	void save(String internshipType) throws AlreadyExistsException;

	void delete(String internshipType) throws NotFoundException;

}
