package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pfa.jobseeking.entity.User;

public interface UserRepository extends Repository<User, Long>{
	List<User> findAll();
	User findUserByUsername(String username);
}

