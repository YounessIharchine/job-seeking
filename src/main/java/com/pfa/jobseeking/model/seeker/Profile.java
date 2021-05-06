package com.pfa.jobseeking.model.seeker;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToOne(mappedBy = "profile")
	Seeker seeker;
	
	String cv;
	String photo;
	String speciality;
	String description;
	String portefolio;
	String github;
	
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	Set<Experience> experiences;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	Set<Education> educations;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	Set<Project> projects;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	Set<Skill> skills;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	Set<Language> languages;
	
	
	
	public Profile() { }

	
	
	public int getId() {
		return id;
	}
	public Seeker getSeeker() {
		return seeker;
	}
	public String getCv() {
		return cv;
	}
	public String getPhoto() {
		return photo;
	}
	public String getSpeciality() {
		return speciality;
	}
	public String getDescription() {
		return description;
	}
	public String getPortefolio() {
		return portefolio;
	}
	public String getGithub() {
		return github;
	}
	public Set<Experience> getExperiences() {
		return experiences;
	}
	public Set<Education> getEducations() {
		return educations;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public Set<Skill> getSkills() {
		return skills;
	}
	public Set<Language> getLanguages() {
		return languages;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	public void setCv(String cv) {
		this.cv = cv;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPortefolio(String portefolio) {
		this.portefolio = portefolio;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public void setExperiences(Set<Experience> experiences) {
		this.experiences = experiences;
	}
	public void setEducations(Set<Education> educations) {
		this.educations = educations;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
	public void setLanguages(Set<Language> languages) {
		this.languages = languages;
	}
	
}
