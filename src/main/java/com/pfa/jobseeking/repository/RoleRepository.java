package com.pfa.jobseeking.repository;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pfa.jobseeking.model.user.Role;

@RepositoryRestResource(exported = false)
public interface RoleRepository extends Repository<Role, Long> {
	Role save(Role role);
}
