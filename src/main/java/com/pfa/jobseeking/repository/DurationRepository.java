package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Duration;

public interface DurationRepository extends CrudRepository<Duration, Integer> {

	Duration findByDuration(String duration);
	
}
