package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

	Company findCompanyByName(String name);
	
	List<Company> findAll();
	
	@Query("SELECT c FROM Company c "
			+ "WHERE c.name LIKE %?1%")
	List<Company> findAllByName(String name);

	@Query("SELECT c FROM Company c "
			+ "WHERE c.domain.name = ?1")
	List<Company> findAllByDomain(String domain);

	@Query("SELECT c FROM Company c "
			+ "WHERE c.name LIKE %?1% "
			+ "AND c.domain.name = ?2")
	List<Company> findAllByNameAndDomain(String name, String domain);

}
