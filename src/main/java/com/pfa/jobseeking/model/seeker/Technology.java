package com.pfa.jobseeking.model.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Technology {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String name;
	
	@ManyToOne
	@JoinColumn(name = "skill_id")
	Skill skill;
	
	
	
	public Technology() { }


	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Skill getSkill() {
		return skill;
	}


	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
}
