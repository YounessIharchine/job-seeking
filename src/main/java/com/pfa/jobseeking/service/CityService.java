package com.pfa.jobseeking.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pfa.jobseeking.model.City;

public interface CityService {

	City save(City city);
	
	List<City> findAll();
	
	List<City> findAll(Pageable pageable);
	
	City findByName(String name);
	
}
