package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Language;

public interface LanguageRepository extends CrudRepository<Language, Integer> {
	
	Language findById(int id);

}
