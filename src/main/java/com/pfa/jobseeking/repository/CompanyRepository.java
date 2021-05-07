package com.pfa.jobseeking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.user.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {

}
