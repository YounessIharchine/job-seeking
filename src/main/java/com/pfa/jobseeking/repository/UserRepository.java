package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pfa.jobseeking.model.user.User;

public interface UserRepository extends Repository<User, Long> {
	List<User> findAll();
	User findUserByEmail(String email);
	User save(User user);
}

