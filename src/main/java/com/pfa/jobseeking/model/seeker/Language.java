package com.pfa.jobseeking.model.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	String level;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	
	public Language() { }
	
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLevel() {
		return level;
	}
	public Profile getProfile() {
		return profile;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
