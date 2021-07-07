package com.pfa.jobseeking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Application;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.user.Seeker;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

	@Query("SELECT a FROM Application a "
			+ "WHERE a.seeker = ?1 "
			+ "AND a.offer = ?2")
	Application findBySeekerAndOffer(Seeker seeker, Offer offer);
	
}
