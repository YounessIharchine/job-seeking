package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.dto.CompanyDto;
import com.pfa.jobseeking.rest.dto.PasswordChangeDto;
import com.pfa.jobseeking.rest.dto.UserDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.DoesNotMatchException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.response.UserResponse;

public interface UserService {

	//**********************************REGISTRATION**********************************

	void saveSeeker(UserDto seekerDto) throws AlreadyExistsException;

	void saveCompany(CompanyDto companyDto) throws AlreadyExistsException, IOException;
	
	
	//**********************************AUTHENTICATED USER INFO**********************************
	
	UserResponse getAuthenticatedUserInfo() throws AccessDeniedException;

	
	//**********************************PASSWORD CHANGE**********************************

	void checkInfo(String email, String code) throws NotFoundException, DoesNotMatchException;

	void changePassword(PasswordChangeDto passwordChangeDto) throws DoesNotMatchException;

	
	
	
	//IDK WILL CHECK LATER
	List<User> findAll();
		
	User findById(int id) throws NotFoundException;
	
	User findUserByEmail(String email) throws NotFoundException;
	
	User save(User user);




}
