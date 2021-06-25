package com.pfa.jobseeking.service;

import java.util.List;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.dto.UserDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.exception.UnauthorizedException;
import com.pfa.jobseeking.rest.response.UserResponse;

public interface UserService {

	List<User> findAll();
		
	User findById(int id) throws NotFoundException;
	
	User findUserByEmail(String email) throws NotFoundException;
	
	User save(User user);

	void save(UserDto user) throws AlreadyExistsException;

	UserResponse getAuthenticatedUserInfo() throws UnauthorizedException;

}
