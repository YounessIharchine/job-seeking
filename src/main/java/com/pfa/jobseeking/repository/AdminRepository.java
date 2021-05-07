package com.pfa.jobseeking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.user.Admin;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {

}
