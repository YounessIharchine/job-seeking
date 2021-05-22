package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferRepository extends CrudRepository<Offer, Integer> {

	List<Offer> findAll();
	List<Offer> findOfferByDomainName(String name);
	List<Offer> findOfferByTitleContainsOrDescriptionContains(String keyword1, String keyword2);
	List<Offer> findOfferByDomainNameAndTitleContainsOrDomainNameAndDescriptionContains(String name1, String keyword1, String name2, String keyword2);
	
}
