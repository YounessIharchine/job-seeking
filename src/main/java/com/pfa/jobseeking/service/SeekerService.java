package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.lowagie.text.DocumentException;
import com.pfa.jobseeking.model.seeker.Education;
import com.pfa.jobseeking.model.seeker.Experience;
import com.pfa.jobseeking.model.seeker.Language;
import com.pfa.jobseeking.model.seeker.Project;
import com.pfa.jobseeking.model.seeker.Skill;
import com.pfa.jobseeking.model.seeker.Technology;
import com.pfa.jobseeking.rest.dto.ApplicationDto;
import com.pfa.jobseeking.rest.dto.ApplicationWithoutCvDto;
import com.pfa.jobseeking.rest.dto.EducationDto;
import com.pfa.jobseeking.rest.dto.ExperienceDto;
import com.pfa.jobseeking.rest.dto.LanguageDto;
import com.pfa.jobseeking.rest.dto.NameDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.ProjectDto;
import com.pfa.jobseeking.rest.dto.SeekerAccountDto;
import com.pfa.jobseeking.rest.dto.SeekerDto;
import com.pfa.jobseeking.rest.dto.SeekerStepOneDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerResponse;

public interface SeekerService {
	
	//***************************FIND SEEKERS***************************
	
	List<SeekerResponse> findSeekers() throws IOException;

	
	//***************************PUBLIC PROFILE***************************
	
	SeekerDto findSeeker(int id) throws IOException, NotFoundException;
	
	PhotoDto findSeekerPhoto(int id) throws NotFoundException, IOException;
	
	
	//***************************OWN PROFILE***************************
	
	SeekerDto updateInfo(SeekerDto seekerDto) throws IOException;
	
	PhotoDto updatePhoto(PhotoDto photoDto) throws IOException;
	
	
	//***************************ACCOUNT***************************
	
	SeekerAccountDto fetchSeekerAccount();
	
	SeekerAccountDto updateSeekerAccount(SeekerAccountDto seekerAccountDto);

	
	//***************************STEP ONE***************************
	
	SeekerStepOneDto fetchSeekerStepOne();
	
	SeekerStepOneDto updateSeekerStepOne(SeekerStepOneDto seekerStepOneDto);
	
	
	//***************************SAVE***************************
	
	List<OfferResponse> findSavedOffers();

	List<OfferResponse> save(int id);
	
	List<OfferResponse> unsave(int id);

	
	//***************************FOLLOW***************************
	
	List<FindCompanyResponse> findFollowedCompanies() throws IOException;
	
	List<FindCompanyResponse> follow(String companyName) throws IOException;
	
	List<FindCompanyResponse> unfollow(String companyName) throws IOException;

	
	//***************************APPLICATION***************************
	
	void applyOffer(int id, ApplicationDto applicationDto) throws IOException, DocumentException;
	
	void applyOffer(int id, ApplicationWithoutCvDto applicationWithoutCvDto) throws DocumentException, IOException;
	
	
	//***************************EXPERIENCES***************************
	
	List<Experience> findExperiencesById(int id) throws NotFoundException;

	List<Experience> findExperiences();

	List<Experience> addExperience(ExperienceDto experienceDto);
	
	List<Experience> updateExperience(int id, ExperienceDto experienceDto) throws AccessDeniedException;

	List<Experience> deleteExperience(int id) throws AccessDeniedException;
	
	
	//***************************EDUCATIONS***************************
	
	List<Education> findEducationsById(int id) throws NotFoundException;

	List<Education> findEducations();

	List<Education> addEducation(EducationDto educationDto);
	
	List<Education> updateEducation(int id, EducationDto educationDto) throws AccessDeniedException;

	List<Education> deleteEducation(int id) throws AccessDeniedException;
	
	
	//***************************PROJECTS***************************
	
	List<Project> findProjectsById(int id) throws NotFoundException;
	
	List<Project> findProjects();

	List<Project> addProject(ProjectDto projectDto);
	
	List<Project> updateProject(int id, ProjectDto projectDto) throws AccessDeniedException;

	List<Project> deleteProject(int id) throws AccessDeniedException;
	
	
	//***************************SKILLS***************************
	
	List<Skill> findSkillsById(int id) throws NotFoundException;
	
	List<Skill> findSkills();

	List<Skill> addSkill(NameDto skillDto);
	
	List<Skill> updateSkill(int id, NameDto skillDto) throws AccessDeniedException;

	List<Skill> deleteSkill(int id) throws AccessDeniedException;
	
	
	//***************************TECHNOLOGIES***************************
	
	List<Technology> findTechnologiesById(int id, int skillId) throws NotFoundException, AccessDeniedException;
	
	List<Technology> findTechnologies(int skillId) throws AccessDeniedException;

	List<Technology> addTechnology(NameDto technologyDto, int skillId) throws AccessDeniedException;
	
	List<Technology> updateTechnology(NameDto technologyDto, int skillId, int id) throws AccessDeniedException;

	List<Technology> deleteTechnology(int skillId, int id) throws AccessDeniedException;
	
	
	//***************************LANGUAGES***************************
	
	List<Language> findLanguagesById(int id) throws NotFoundException;
	
	List<Language> findLanguages();

	List<Language> addLanguage(LanguageDto languageDto);
	
	List<Language> updateLanguage(int id, LanguageDto languageDto) throws AccessDeniedException;

	List<Language> deleteLanguage(int id) throws AccessDeniedException;


}
