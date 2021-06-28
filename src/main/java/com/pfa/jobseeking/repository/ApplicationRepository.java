package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.offer.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

}
