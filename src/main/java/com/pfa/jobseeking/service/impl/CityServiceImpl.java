package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	
	@Override
	public City save(City city) {
		return cityRepository.save(city);
	}


	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	


	@Override
	public City findByName(String name) {
		return cityRepository.findCityByName(name);
	}

}





//@Override
//public List<City> findAll(Pageable pageable) {
//	return cityRepository.findAll(pageable).getContent();
//}
