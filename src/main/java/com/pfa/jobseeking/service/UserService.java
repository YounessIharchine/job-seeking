package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.user.User;

public interface UserService {

	List<User> findAll();
	User findUserByEmail(String email);
	User save(User user);

}
