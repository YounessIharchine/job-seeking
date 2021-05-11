package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {

	List<Offer> findAll();
}
