package com.pfa.jobseeking.model.seeker;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
	Set<Technology> technologies;
	
	@ManyToOne
	@JoinColumn(name = "profile_id")
	Profile profile;
	
	
	public Skill() { }


	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Set<Technology> getTechnologies() {
		return technologies;
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
	public void setTechnologies(Set<Technology> technologies) {
		this.technologies = technologies;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
