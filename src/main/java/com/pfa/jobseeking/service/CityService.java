package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;

public interface CityService {

	City save(City city);
	
	List<City> findAll();
		
	City findByName(String name);

	void save(String city) throws AlreadyExistsException;
	
}
