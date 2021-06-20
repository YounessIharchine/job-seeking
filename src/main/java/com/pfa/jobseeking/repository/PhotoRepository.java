package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.company.Photo;

public interface PhotoRepository extends CrudRepository<Photo, Integer> {

}
