package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.entity.User;

public interface UserService {

	List<User> findAll();
	User findUserByUsername(String username);

}
