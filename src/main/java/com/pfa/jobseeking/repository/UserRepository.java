package com.pfa.jobseeking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findAll();
		
	User findById(int id);
	
	User findUserByEmail(String email);
	
	@SuppressWarnings("unchecked")
	User save(User user);
}

