package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.Offer;

public interface InternshipOfferRepository extends CrudRepository<InternshipOffer, Integer> {

	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findAllInternships();
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.internshipType.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByInternshipTypeName(String typeName);
	
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.city.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByCity(String cityName);
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByKeyword(String keyword);
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByDomain(String domainName);
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.internshipType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDType(String domainName, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.internshipType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeywordANDType(String keyword, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.city.name = ?1 "
			+ "AND o.internshipType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByCityANDType(String cityName, String typeName);
	
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByDomainANDKeyword(String domainName, String keyword);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByDomainANDCity(String domainName, String cityName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByKeywordANDCity(String keyword, String cityName);
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.internshipType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDKeywordANDType(String domainName, String keyword, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "AND o.internshipType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDCityANDType(String domainName, String cityName, String typeName);
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "AND o.internshipType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeywordANDCityANDType(String keyword, String cityName, String typeName);
	
	
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findInternshipsByDomainANDKeywordANDCity(String domainName, String keyword, String cityName);
	
	@Query("SELECT o FROM InternshipOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "AND o.internshipType.name = ?4 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDKeywordANDCityANDType(String domainName, String keyword, String cityName, String typeName);
	
}
