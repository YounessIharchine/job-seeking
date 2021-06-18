package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.OfferCreationRequest;

public interface OfferCreationRequestRepository extends CrudRepository<OfferCreationRequest, Integer> {

	List<OfferCreationRequest> findAll();
	
}
