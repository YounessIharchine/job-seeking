package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Seeker;

public interface SeekerRepository extends CrudRepository<Seeker, Integer> {

	List<Seeker> findAll();
	
	@Query("SELECT DISTINCT s FROM Seeker s INNER JOIN s.profile.skills sk JOIN sk.technologies t "
			+ "WHERE sk.name LIKE %?1% OR t.name LIKE %?1%")
	List<Seeker> findAllByKeyword(String keyword);
	
}
