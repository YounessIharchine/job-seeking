package com.pfa.jobseeking.model.seeker;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
	List<Technology> technologies;
	
	@JsonIgnore
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
	public List<Technology> getTechnologies() {
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
	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}
