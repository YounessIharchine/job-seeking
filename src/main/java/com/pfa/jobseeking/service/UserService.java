package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.rest.dto.CompanyDto;
import com.pfa.jobseeking.rest.dto.SeekerDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.exception.UnauthorizedException;
import com.pfa.jobseeking.rest.response.UserResponse;

public interface UserService {

	void saveSeeker(SeekerDto seekerDto) throws AlreadyExistsException;

	void saveCompany(CompanyDto companyDto) throws AlreadyExistsException, IOException;
	
	List<User> findAll();
		
	User findById(int id) throws NotFoundException;
	
	User findUserByEmail(String email) throws NotFoundException;
	
	User save(User user);

	UserResponse getAuthenticatedUserInfo() throws UnauthorizedException;

}
