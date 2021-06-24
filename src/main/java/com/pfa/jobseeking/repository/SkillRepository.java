package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Skill;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
	
	Skill findById(int id);

}
