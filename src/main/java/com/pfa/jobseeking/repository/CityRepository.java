package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.City;

public interface CityRepository extends PagingAndSortingRepository<City, Integer> {

	List<City> findAll();
	
	City findCityByName(String name);
	
}
