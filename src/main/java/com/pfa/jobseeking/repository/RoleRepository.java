package com.pfa.jobseeking.repository;

import org.springframework.data.repository.Repository;

import com.pfa.jobseeking.model.user.Role;

public interface RoleRepository extends Repository<Role, Long> {
	Role save(Role role);
}
