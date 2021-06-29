package com.pfa.jobseeking.model.seeker;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(columnDefinition = "TEXT")
	String portefolio;
	String github;
	
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	List<Experience> experiences;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	List<Education> educations;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	List<Project> projects;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	List<Skill> skills;
	@OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
	List<Language> languages;
	
	
	
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
	public String getPortefolio() {
		return portefolio;
	}
	public String getGithub() {
		return github;
	}
	public List<Experience> getExperiences() {
		return experiences;
	}
	public List<Education> getEducations() {
		return educations;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public List<Skill> getSkills() {
		return skills;
	}
	public List<Language> getLanguages() {
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
	public void setPortefolio(String portefolio) {
		this.portefolio = portefolio;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}
	
	
	public void addExperience(Experience experience) {
		this.experiences.add(experience);
	}
	public void removeExperience(Experience experience) {
		this.experiences.remove(experience);
	}
	
	public void addEducation(Education education) {
		this.educations.add(education);
	}
	public void removeEducation(Education education) {
		this.educations.remove(education);
	}
	
	public void addProject(Project project) {
		this.projects.add(project);
	}
	public void removeProject(Project project) {
		this.projects.remove(project);
	}
	
	public void addSkill(Skill skill) {
		this.skills.add(skill);
	}
	public void removeSkill(Skill skill) {
		this.skills.remove(skill);
	}
	
	public void addLanguage(Language language) {
		this.languages.add(language);
	}
	public void removeLanguage(Language language) {
		this.languages.remove(language);
	}
	
}
