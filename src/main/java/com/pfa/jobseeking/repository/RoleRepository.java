package com.pfa.jobseeking.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfa.jobseeking.model.user.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	@SuppressWarnings("unchecked")
	Role save(Role role);
	
	Role findRoleByName(String name);
}
