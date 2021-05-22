package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.repository.InternshipTypeRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.InternshipTypeService;


@Service
public class InternshipTypeServiceImpl implements InternshipTypeService {

	@Autowired
	InternshipTypeRepository internshipTypeRepository;
	
	@Override
	public List<InternshipType> findAll() {
		return internshipTypeRepository.findAll();
	}

	
	@Transactional
	@Override
	public void save(String internshipTypeName) throws AlreadyExistsException {
		
		if(internshipTypeRepository.findInternshipTypeByName(internshipTypeName) != null)
			throw new AlreadyExistsException("This Internship Type already exists in the database.");
		
		InternshipType internshipType = new InternshipType(internshipTypeName);
		
		internshipTypeRepository.save(internshipType);
	}

	
	@Transactional
	@Override
	public void delete(String internshipTypeName) throws NotFoundException {

		if(internshipTypeRepository.findInternshipTypeByName(internshipTypeName) == null)
			throw new NotFoundException("This Internship Type doesn't exist in the database.");
		
		internshipTypeRepository.deleteInternshipTypeByName(internshipTypeName);
		
	}

}
