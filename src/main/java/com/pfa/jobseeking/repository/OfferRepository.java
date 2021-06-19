package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferRepository extends CrudRepository<Offer, Integer> {

	Offer findById(int id);
	
	List<Offer> findAll();
	List<Offer> findByDomainName(String domainName);
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.title LIKE %?1% "
			+ "OR o.description LIKE %?1%")
	List<Offer> findByKeyword(String keyword);
	@Query("SELECT o FROM Offer o "
			+ "WHERE (o.title LIKE %?1% "
			+ "OR o.description LIKE %?1%) "
			+ "AND o.domain.name = ?2")
	List<Offer> findByKeywordANDDomainName(String keyword, String domainName);
	
//	List<Offer> findOfferByTitleContainsOrDescriptionContains(String keyword1, String keyword2);
//	List<Offer> findOfferByDomainNameAndTitleContainsOrDomainNameAndDescriptionContains(String name1, String keyword1, String name2, String keyword2);
	
}
