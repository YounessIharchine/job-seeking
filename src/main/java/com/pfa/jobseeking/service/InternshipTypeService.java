package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface InternshipTypeService {

	List<InternshipType> findAll();

	List<InternshipType> save(NameDto internshipType) throws AlreadyExistsException;

	List<InternshipType> delete(String internshipType) throws NotFoundException;

}
