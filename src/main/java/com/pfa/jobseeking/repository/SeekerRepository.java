package com.pfa.jobseeking.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.pfa.jobseeking.model.user.Seeker;

public interface SeekerRepository extends PagingAndSortingRepository<Seeker, Integer> {

}
