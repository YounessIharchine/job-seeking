package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.seeker.Project;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
	
	Project findById(int id);

}
