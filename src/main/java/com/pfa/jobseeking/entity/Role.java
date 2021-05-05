package com.pfa.jobseeking.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Role {

	@Id
	int id;
	
	String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	Set<User> users;
	
	
	
	public Role() { }


	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<User> getUsers() {
		return users;
	}

	

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
}
