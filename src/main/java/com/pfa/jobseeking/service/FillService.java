package com.pfa.jobseeking.service;

import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface FillService {

	void fill() throws NotFoundException, AlreadyExistsException;
	
}
