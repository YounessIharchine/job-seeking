package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;

public interface JobOfferRepository extends CrudRepository<JobOffer, Integer> {
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findAllJobs();

	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.jobType.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByJobTypeName(String name);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.city.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByCity(String cityName);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByKeyword(String keyword);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByDomain(String domainName);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.jobType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDType(String domainName, String typeName);
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.jobType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeywordANDType(String keyword, String typeName);
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.city.name = ?1 "
			+ "AND o.jobType.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByCityANDType(String cityName, String typeName);
	
	
	
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByKeywordANDCity(String keyword, String cityName);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByDomainANDCity(String domainName, String cityName);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByDomainANDKeyword(String domainName, String keyword);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.jobType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDKeywordANDType(String domainName, String keyword, String typeName);
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND o.city.name = ?2 "
			+ "AND o.jobType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDCityANDType(String domainName, String cityName, String typeName);
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE (o.title LIKE %?1% OR o.description LIKE %?1%) "
			+ "AND o.city.name = ?2 "
			+ "AND o.jobType.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByKeywordANDCityANDType(String keyword, String cityName, String typeName);
	
	
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findJobsByDomainANDKeywordANDCity(String domainName, String keyword, String cityName);
	
	@Query("SELECT o FROM JobOffer o "
			+ "WHERE o.domain.name = ?1 "
			+ "AND (o.title LIKE %?2% OR o.description LIKE %?2%) "
			+ "AND o.city.name = ?3 "
			+ "AND o.jobType.name = ?4 "
			+ "AND o.isOpen = TRUE "
			+ "AND o.isVerified = TRUE "
			+ "ORDER BY STR_TO_DATE(o.date, '%d-%m-%Y') DESC")
	List<Offer> findByDomainANDKeywordANDCityANDType(String domainName, String keyword, String cityName, String typeName);
	
}
