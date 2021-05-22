package com.pfa.jobseeking.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.dto.UserDto;
import com.pfa.jobseeking.rest.exception.EmailExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;

public interface UserService {

	List<User> findAll();
	
	List<User> findAll(Pageable pageable);
	
	User findById(int id) throws NotFoundException;
	
	User findUserByEmail(String email) throws NotFoundException;
	
	User save(User user);

	void save(UserDto user) throws EmailExistsException;

}
