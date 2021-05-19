package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.repository.InternshipTypeRepository;
import com.pfa.jobseeking.service.InternshipTypeService;


@Service
public class InternshipTypeServiceImpl implements InternshipTypeService {

	@Autowired
	InternshipTypeRepository internshipTypeRepository;
	
	@Override
	public List<InternshipType> findAll() {
		return internshipTypeRepository.findAll();
	}

}
