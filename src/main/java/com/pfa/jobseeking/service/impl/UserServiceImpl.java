package com.pfa.jobseeking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	
	
	@Override
	public List<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable).getContent();
	}

	
	
	@Override
	public User findById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		
		if(user == null)
			throw new NotFoundException("The id does not correspond to any user");
		
		return user;
	}
	
	
	
	@Override
	public User findUserByEmail(String email) throws NotFoundException {
		User user = userRepository.findUserByEmail(email);
		
		if(user == null)
			throw new NotFoundException("There is no user with that email");
		
		return user;
	}

	
	
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}



}