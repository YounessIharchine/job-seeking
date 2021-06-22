package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.Offer;

public interface InternshipOfferRepository extends CrudRepository<InternshipOffer, Integer> {

	List<Offer> findByInternshipTypeName(String typeName);
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.internshipType.name = ?2")
	List<Offer> findByDomainANDType(String domainName, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.internshipType.name = ?2")
	List<Offer> findByKeywordANDType(String keyword, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.city.name = ?1 "
			+ "AND o.internshipType.name = ?2")
	List<Offer> findByCityANDType(String cityName, String typeName);
	
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.internshipType.name = ?3")
	List<Offer> findByDomainANDKeywordANDType(String domainName, String keyword, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "AND o.internshipType.name = ?3")
	List<Offer> findByDomainANDCityANDType(String domainName, String cityName, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "AND o.internshipType.name = ?3")
	List<Offer> findByKeywordANDCityANDType(String keyword, String cityName, String typeName);
	
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "AND o.internshipType.name = ?4")
	List<Offer> findByDomainANDKeywordANDCityANDType(String domainName, String keyword, String cityName, String typeName);
	
}