package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface UserService {

	
	List<User> findAll();
	
	User findById(int id) throws NotFoundException;
	
	User findUserByEmail(String email) throws NotFoundException;
	
	User save(User user);

}
