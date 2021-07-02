package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Offer;

public interface OfferRepository extends CrudRepository<Offer, Integer> {

	Offer findById(int id);
	
	@Query("SELECT o FROM Offer o "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findAll();
	
	
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.title LIKE %?1% OR o.description LIKE %?1% "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeyword(String keyword);
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.domain.name = ?1 "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainName(String domainName);
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.city.name = ?1 "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByCityName(String cityName);
	
	
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainNameANDKeyword(String domainName, String Keyword);
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainNameANDCity(String domainName, String cityName);
	@Query("SELECT o FROM Offer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeywordANDCity(String keyword, String cityName);
	
	
	@Query("SELECT o FROM Offer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainNameANDKeywordANDCity(String domainName, String keyword, String cityName);
	
	
	
	
	
//	List<Offer> findOfferByTitleContainsOrDescriptionContains(String keyword1, String keyword2);
//	List<Offer> findOfferByDomainNameAndTitleContainsOrDomainNameAndDescriptionContains(String name1, String keyword1, String name2, String keyword2);
	
}
