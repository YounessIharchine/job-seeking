package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Seeker;

public interface SeekerRepository extends CrudRepository<Seeker, Integer> {

	List<Seeker> findAll();
	
}
