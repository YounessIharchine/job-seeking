package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {

	List<Offer> findAll();
	List<Offer> findOfferByDomainName(String name);
	List<Offer> findOfferByTitleContainsOrDescriptionContains(String keyword1, String keyword2);
	List<Offer> findOfferByDomainNameAndTitleContainsOrDomainNameAndDescriptionContains(String name1, String keyword1, String name2, String keyword2);
	
}
