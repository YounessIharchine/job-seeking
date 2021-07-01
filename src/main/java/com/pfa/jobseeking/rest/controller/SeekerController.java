package com.pfa.jobseeking.rest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerResponse;
import com.pfa.jobseeking.service.SeekerService;

@RestController
public class SeekerController {

	@Autowired
	SeekerService seekerService;
	
	
	
	//**********************************FIND SEEKERS**********************************
	@GetMapping("${rest.api.basePath}/seekers")
	List<SeekerResponse> getSeekers() throws IOException {
		return seekerService.findSeekers();
	}
	
	
	
	//**********************************PUBLIC PROFILE**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}")
	SeekerDto getSeeker(@PathVariable int id) throws IOException, NotFoundException {
		return seekerService.findSeeker(id);
	}

	@GetMapping("${rest.api.basePath}/seekers/{id}/photo")
	PhotoDto getSeekerPhoto(@PathVariable int id) throws IOException, NotFoundException {
		return seekerService.findSeekerPhoto(id);
	}
	
	
	
	//**********************************OWN PROFILE**********************************
	@PatchMapping("${rest.api.basePath}/seekers/profile")
	SeekerDto updateInfo(@RequestBody SeekerDto seekerDto) throws IOException {
		return seekerService.updateInfo(seekerDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/profile/photo")
	PhotoDto updatePhoto(@RequestBody PhotoDto photoDto) throws IOException {
		return seekerService.updatePhoto(photoDto);
	}
	
	
	
	//**********************************ACCOUNT**********************************
	@GetMapping("${rest.api.basePath}/seekers/account")
	SeekerAccountDto getSeekerAccount() {
		return seekerService.fetchSeekerAccount();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/account")
	SeekerAccountDto updateSeekerAccount(@RequestBody SeekerAccountDto seekerAccountDto) {
		return seekerService.updateSeekerAccount(seekerAccountDto);
	}
	
	
	
	//**********************************STEP ONE**********************************
	@GetMapping("${rest.api.basePath}/seekers/stepOne")
	SeekerStepOneDto getSeekerStepOne() {
		return seekerService.fetchSeekerStepOne();
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/stepOne")
	SeekerStepOneDto updateSeekerStepOne(@RequestBody SeekerStepOneDto seekerStepOneDto) {
		return seekerService.updateSeekerStepOne(seekerStepOneDto);
	}
	
	
	
	//**********************************SAVING**********************************
	@GetMapping("${rest.api.basePath}/seekers/savedOffers")
	List<OfferResponse> showSavedOffers() {
		return seekerService.findSavedOffers();
	}
	
	@GetMapping("${rest.api.basePath}/seekers/saveOffer/{id}")
	void saveOffer(@PathVariable int id) {
		seekerService.save(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/unsaveOffer/{id}")
	void unsaveOffer(@PathVariable int id) {
		seekerService.unsave(id);
	}
	
	
	
	//**********************************FOLLOWING**********************************
	@GetMapping("${rest.api.basePath}/seekers/followCompany")
	void followCompany(@RequestParam String companyName) {
		seekerService.follow(companyName);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/unfollowCompany")
	void unfollowCompany(@RequestParam String companyName) {
		seekerService.unfollow(companyName);
	}
	
	

	//**********************************APPLYING**********************************
	@PostMapping("${rest.api.basePath}/seekers/applyOffer/{id}")
	void applyOffer(@PathVariable int id, @RequestBody ApplicationDto applicationDto) throws IOException {
		seekerService.applyOffer(id, applicationDto);
	}
	
	@PostMapping("${rest.api.basePath}/seekers/applyOfferWithoutCv/{id}")
	void applyOfferWithoutCv(@PathVariable int id, @RequestBody ApplicationWithoutCvDto applicationWithoutCvDto) throws IOException, DocumentException {
		seekerService.applyOffer(id, applicationWithoutCvDto);
	}
	
	
	//**********************************EXPERIENCES**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/experiences")
	List<Experience> getExperiencesById(@PathVariable int id) throws NotFoundException {
		return seekerService.findExperiencesById(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/experiences")
	List<Experience> getExperiences() {
		return seekerService.findExperiences();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/experiences")
	List<Experience> addExperience(@RequestBody ExperienceDto experienceDto) {
		return seekerService.addExperience(experienceDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/experiences/{id}")
	List<Experience> updateExperience(@PathVariable int id, @RequestBody ExperienceDto experienceDto) throws AccessDeniedException {
		return seekerService.updateExperience(id, experienceDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/experiences/{id}")
	List<Experience> removeExperience(@PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteExperience(id);
	}
	
	
	
	//**********************************EDUCATIONS**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/educations")
	List<Education> getEducationsById(@PathVariable int id) throws NotFoundException {
		return seekerService.findEducationsById(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/educations")
	List<Education> getEducations() {
		return seekerService.findEducations();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/educations")
	List<Education> addEducation(@RequestBody EducationDto educationDto) {
		return seekerService.addEducation(educationDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/educations/{id}")
	List<Education> updateEducation(@PathVariable int id, @RequestBody EducationDto educationDto) throws AccessDeniedException {
		return seekerService.updateEducation(id, educationDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/educations/{id}")
	List<Education> removeEducation(@PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteEducation(id);
	}
	
	
	
	//**********************************PROJECTS**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/projects")
	List<Project> getProjectsById(@PathVariable int id) throws NotFoundException {
		return seekerService.findProjectsById(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/projects")
	List<Project> getProjects() {
		return seekerService.findProjects();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/projects")
	List<Project> addProject(@RequestBody ProjectDto projectDto) {
		return seekerService.addProject(projectDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/projects/{id}")
	List<Project> updateProject(@PathVariable int id, @RequestBody ProjectDto projectDto) throws AccessDeniedException {
		return seekerService.updateProject(id, projectDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/projects/{id}")
	List<Project> removeProject(@PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteProject(id);
	}	
	
	
	
	//**********************************SKILLS**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/skills")
	List<Skill> getSkillsById(@PathVariable int id) throws NotFoundException {
		return seekerService.findSkillsById(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/skills")
	List<Skill> getSkills() {
		return seekerService.findSkills();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/skills")
	List<Skill> addSkill(@RequestBody NameDto skillDto) {
		return seekerService.addSkill(skillDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/skills/{id}")
	List<Skill> updateSkill(@PathVariable int id, @RequestBody NameDto skillDto) throws AccessDeniedException {
		return seekerService.updateSkill(id, skillDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/skills/{id}")
	List<Skill> removeSkill(@PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteSkill(id);
	}
	
	
	
	//**********************************TECHNOLOGIES**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/skills/{skillId}/technologies")
	List<Technology> getTechnologiesById(@PathVariable int id, @PathVariable int skillId) throws AccessDeniedException, NotFoundException {
		return seekerService.findTechnologiesById(id, skillId);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/skills/{skillId}/technologies")
	List<Technology> getTechnologies(@PathVariable int skillId) throws AccessDeniedException {
		return seekerService.findTechnologies(skillId);
	}
	
	@PostMapping("${rest.api.basePath}/seekers/skills/{skillId}/technologies")
	List<Technology> addTechnology(@RequestBody NameDto technologyDto, @PathVariable int skillId) throws AccessDeniedException {
		return seekerService.addTechnology(technologyDto, skillId);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/skills/{skillId}/technologies/{id}")
	List<Technology> updateTechnology(@PathVariable int skillId, @PathVariable int id, @RequestBody NameDto technologyDto) throws AccessDeniedException {
		return seekerService.updateTechnology(technologyDto, skillId, id);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/skills/{skillId}/technologies/{id}")
	List<Technology> removeTechnology(@PathVariable int skillId, @PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteTechnology(skillId, id);
	}
	
	
	
	//**********************************LANGUAGES**********************************
	@GetMapping("${rest.api.basePath}/seekers/{id}/languages")
	List<Language> getLanguagesById(@PathVariable int id) throws NotFoundException {
		return seekerService.findLanguagesById(id);
	}
	
	@GetMapping("${rest.api.basePath}/seekers/languages")
	List<Language> getLanguages() {
		return seekerService.findLanguages();
	}
	
	@PostMapping("${rest.api.basePath}/seekers/languages")
	List<Language> addLanguage(@RequestBody LanguageDto languageDto) {
		return seekerService.addLanguage(languageDto);
	}
	
	@PatchMapping("${rest.api.basePath}/seekers/languages/{id}")
	List<Language> updateLanguage(@PathVariable int id, @RequestBody LanguageDto languageDto) throws AccessDeniedException {
		return seekerService.updateLanguage(id, languageDto);
	}
	
	@DeleteMapping("${rest.api.basePath}/seekers/languages/{id}")
	List<Language> removeLanguage(@PathVariable int id) throws AccessDeniedException {
		return seekerService.deleteLanguage(id);
	}
	
	
}
