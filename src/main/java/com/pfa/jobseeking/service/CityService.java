package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.City;

public interface CityService {

	City save(City city);
	
	List<City> findAll();
		
	City findByName(String name);
	
}
