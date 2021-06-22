package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface CityService {
	
	List<City> findAll();

	List<City> save(NameDto city) throws AlreadyExistsException;

	List<City> delete(String city) throws NotFoundException;
	
	
	//for filling database
	City save(City city);
	City findByName(String name);
	
}
