package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
