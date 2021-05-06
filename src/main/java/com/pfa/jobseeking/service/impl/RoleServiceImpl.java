package com.pfa.jobseeking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

}
