package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	CityRepository cityRepository;
	
	

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}
	


	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<City> save(NameDto cityName) throws AlreadyExistsException {
		
		if(cityRepository.findCityByName(cityName.getName()) != null)
			throw new AlreadyExistsException("This city already exists in the database.");
		
		City city = new City(cityName.getName());
		
		cityRepository.save(city);
		
		return cityRepository.findAll();
		
	}


	@Transactional
	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<City> delete(String cityName) throws NotFoundException {
		
		if(cityRepository.findCityByName(cityName) == null)
			throw new NotFoundException("This city doesn't exist in the database.");
		
		cityRepository.deleteCityByName(cityName);
		
		return cityRepository.findAll();
		
	}
	
	
	
	
	
	
	
	
	//for filling database
	@Override
	public City save(City city) {
		return cityRepository.save(city);
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
