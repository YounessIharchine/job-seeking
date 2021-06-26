package com.pfa.jobseeking.service;

import java.io.IOException;

import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface FillService {

	void fill() throws NotFoundException, AlreadyExistsException, IOException;
	
}
