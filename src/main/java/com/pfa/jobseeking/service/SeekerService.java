package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pfa.jobseeking.model.seeker.Education;
import com.pfa.jobseeking.model.seeker.Experience;
import com.pfa.jobseeking.model.seeker.Language;
import com.pfa.jobseeking.model.seeker.Project;
import com.pfa.jobseeking.model.seeker.Skill;
import com.pfa.jobseeking.model.seeker.Technology;
import com.pfa.jobseeking.rest.dto.EducationDto;
import com.pfa.jobseeking.rest.dto.ExperienceDto;
import com.pfa.jobseeking.rest.dto.LanguageDto;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.dto.ProjectDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerAccountResponse;
import com.pfa.jobseeking.rest.response.SeekerProfileResponse;
import com.pfa.jobseeking.rest.response.SeekerStepOneResponse;

public interface SeekerService {

	void updateInfo(Map<String, String> map) throws IOException;

	void save(int id);
	
	void unsave(int id);

	void follow(String companyName);
	
	void unfollow(String companyName);

	List<OfferResponse> findSavedOffers();

	SeekerProfileResponse findSeeker(int id) throws IOException, NotFoundException;

	SeekerAccountResponse fetchSeekerAccount();

	SeekerStepOneResponse fetchSeekerStepOne();
	
	
	//*****************EXPERIENCES*****************
	
	List<Experience> findExperiencesById(int id) throws NotFoundException;

	List<Experience> findExperiences();

	List<Experience> addExperience(ExperienceDto experienceDto);

	List<Experience> deleteExperience(int id) throws AccessDeniedException;
	
	
	//*****************EDUCATIONS*****************
	
	List<Education> findEducationsById(int id) throws NotFoundException;

	List<Education> findEducations();

	List<Education> addEducation(EducationDto educationDto);

	List<Education> deleteEducation(int id) throws AccessDeniedException;
	
	
	//*****************PROJECTS*****************
	
	List<Project> findProjectsById(int id) throws NotFoundException;
	
	List<Project> findProjects();

	List<Project> addProject(ProjectDto projectDto);

	List<Project> deleteProject(int id) throws AccessDeniedException;
	
	
	//*****************SKILLS*****************
	
	List<Skill> findSkillsById(int id) throws NotFoundException;
	
	List<Skill> findSkills();

	List<Skill> addSkill(NameDto skillDto);

	List<Skill> deleteSkill(int id) throws AccessDeniedException;
	
	
	//*****************TECHNOLOGIES*****************
	
	List<Technology> findTechnologiesById(int id, int skillId) throws NotFoundException, AccessDeniedException;
	
	List<Technology> findTechnologies(int skillId) throws AccessDeniedException;

	List<Technology> addTechnology(NameDto technologyDto, int skillId) throws AccessDeniedException;

	List<Technology> deleteTechnology(int skillId, int id) throws AccessDeniedException;
	
	
	//*****************LANGUAGES*****************
	
	List<Language> findLanguagesById(int id) throws NotFoundException;
	
	List<Language> findLanguages();

	List<Language> addLanguage(LanguageDto languageDto);

	List<Language> deleteLanguage(int id) throws AccessDeniedException;



}
