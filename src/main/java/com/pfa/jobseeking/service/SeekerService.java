package com.pfa.jobseeking.service;

import java.util.Map;

import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface SeekerService {

	void update(Map<String, String> map, int id) throws NotFoundException;

	void save(int id);

}
