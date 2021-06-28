package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.Education;
import com.pfa.jobseeking.model.seeker.Experience;
import com.pfa.jobseeking.model.seeker.Language;
import com.pfa.jobseeking.model.seeker.Project;
import com.pfa.jobseeking.model.seeker.Skill;
import com.pfa.jobseeking.model.seeker.Technology;
import com.pfa.jobseeking.model.seeker.TimePeriod;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.CompanyRepository;
import com.pfa.jobseeking.repository.EducationRepository;
import com.pfa.jobseeking.repository.ExperienceRepository;
import com.pfa.jobseeking.repository.LanguageRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.ProjectRepository;
import com.pfa.jobseeking.repository.SkillRepository;
import com.pfa.jobseeking.repository.TechnologyRepository;
import com.pfa.jobseeking.repository.UserRepository;
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
import com.pfa.jobseeking.service.SeekerService;

@Service
public class SeekerServiceImpl implements SeekerService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	EducationRepository educationRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	SkillRepository skillRepository;
	
	@Autowired
	TechnologyRepository technologyRepository;
	
	@Autowired
	LanguageRepository languageRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	
	//**********************************PUBLIC PROFILE**********************************
	
	@Override
	public SeekerDto findSeeker(int id) throws IOException, NotFoundException {
		SeekerDto response = new SeekerDto();
		
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
		
		seeker = (Seeker)user;
		
		response.setEmail(seeker.getEmail());
		response.setFirstName(seeker.getFirstName());
		response.setLastName(seeker.getLastName());
		response.setPhone(seeker.getPhone());
		response.setAddress(seeker.getAddress());
		response.setBirthDate(seeker.getBirthDate());
		response.setCity(seeker.getCity().getName());
		response.setSpeciality(seeker.getProfile().getSpeciality());
		response.setPortefolio(seeker.getProfile().getPortefolio());
		response.setGithub(seeker.getProfile().getGithub());

		return response;
	}
	
	
	@Override
	public PhotoDto findSeekerPhoto(int id) throws NotFoundException, IOException {
		PhotoDto response = new PhotoDto();
		
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
		
		seeker = (Seeker)user;
		
		byte[] photoBytes = FileUtils.readFileToByteArray(new File(path+seeker.getProfile().getPhoto()));
		response.setPhoto(Base64.getEncoder().encodeToString(photoBytes));
		
		return response;
	}
	
	
	
	
	//**********************************OWN PROFILE**********************************
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerDto updateInfo(SeekerDto seekerDto) throws IOException {
		Seeker seeker = getAuthenticatedSeeker();
		
		seekerDto.setEmail(seeker.getEmail());
		seeker.setFirstName(seekerDto.getFirstName());
		seeker.setLastName(seekerDto.getLastName());
		seeker.setPhone(seekerDto.getPhone());
		seeker.setAddress(seekerDto.getAddress());
		seeker.setBirthDate(seekerDto.getBirthDate());
		seeker.setCity(cityRepository.findCityByName(seekerDto.getCity()));
		seeker.getProfile().setSpeciality(seekerDto.getSpeciality());
		seeker.getProfile().setGithub(seekerDto.getGithub());
		seeker.getProfile().setPortefolio(seekerDto.getPortefolio());
		
		return seekerDto;
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public PhotoDto updatePhoto(PhotoDto photoDto) throws IOException {
		Seeker seeker = getAuthenticatedSeeker();
		
		byte[] photoBytes = Base64.getDecoder().decode(photoDto.getPhoto());
		FileUtils.writeByteArrayToFile(new File(path+seeker.getProfile().getPhoto()), photoBytes);
		
		return photoDto;
	}
	
	
	
	
	//**********************************ACCOUNT**********************************
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerAccountDto fetchSeekerAccount() {
		SeekerAccountDto response = new SeekerAccountDto();
		Seeker seeker = getAuthenticatedSeeker();
		
		response.setEmail(seeker.getEmail());
		response.setFirstName(seeker.getFirstName());
		response.setLastName(seeker.getLastName());
		response.setPhone(seeker.getPhone());
		response.setAddress(seeker.getAddress());
		response.setBirthDate(seeker.getBirthDate());
		if(seeker.getCity()!=null)
			response.setCity(seeker.getCity().getName());
		else
			response.setCity(null);
		
		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerAccountDto updateSeekerAccount(SeekerAccountDto seekerAccountDto) {
		SeekerAccountDto response = seekerAccountDto;
		Seeker seeker = getAuthenticatedSeeker();
		
		seeker.setEmail(response.getEmail());
		seeker.setFirstName(response.getFirstName());
		seeker.setLastName(response.getLastName());
		seeker.setPhone(response.getPhone());
		seeker.setAddress(response.getAddress());
		seeker.setBirthDate(response.getBirthDate());
		seeker.setCity(cityRepository.findCityByName(response.getCity()));
		
		return response;
	}
	
	
	
	
	//**********************************STEP ONE**********************************
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerStepOneDto fetchSeekerStepOne() {
		SeekerStepOneDto response = new SeekerStepOneDto();
		Seeker seeker = getAuthenticatedSeeker();
		
		response.setFirstName(seeker.getFirstName());
		response.setLastName(seeker.getLastName());
		response.setPhone(seeker.getPhone());
		response.setAddress(seeker.getAddress());
		response.setBirthDate(seeker.getBirthDate());
		if(seeker.getCity()!=null)
			response.setCity(seeker.getCity().getName());
		else
			response.setCity(null);
		
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerStepOneDto updateSeekerStepOne(SeekerStepOneDto seekerStepOneDto) {
		SeekerStepOneDto response = seekerStepOneDto;
		Seeker seeker = getAuthenticatedSeeker();
		
		seeker.setFirstName(response.getFirstName());
		seeker.setLastName(response.getLastName());
		seeker.setPhone(response.getPhone());
		seeker.setAddress(response.getAddress());
		seeker.setBirthDate(response.getBirthDate());
		seeker.setCity(cityRepository.findCityByName(response.getCity()));
		
		return seekerStepOneDto;
	}
	
	
	
	
	//**********************************SAVE**********************************

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Override
	public List<OfferResponse> findSavedOffers() {
		Seeker seeker = getAuthenticatedSeeker();

		return mapToResponse(seeker.getOffers());
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void save(int id) {
		Seeker seeker = getAuthenticatedSeeker();
		seeker.saveOffer(offerRepository.findById(id));
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void unsave(int id) {
		Seeker seeker = getAuthenticatedSeeker();
		seeker.unsaveOffer(offerRepository.findById(id));
	}
	
	
	
	
	//**********************************FOLLOW**********************************
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void follow(String companyName) {
		Seeker seeker = getAuthenticatedSeeker();
		Company company = companyRepository.findCompanyByName(companyName);
		seeker.followCompany(company);
		company.getCompanyNotification().incrementNewFollowers();
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void unfollow(String companyName) {
		Seeker seeker = getAuthenticatedSeeker();
		seeker.unfollowCompany(companyRepository.findCompanyByName(companyName));
	}
	
	
	
	
	//**********************************APPLICATION**********************************


	
	
	//**********************************EXPERIENCES**********************************
	
	@Transactional
	@Override
	public List<Experience> findExperiencesById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
			
		return seeker.getProfile().getExperiences();
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Experience> findExperiences() {
		Seeker seeker = getAuthenticatedSeeker();
		return seeker.getProfile().getExperiences();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Experience> addExperience(ExperienceDto experienceDto) {
		Seeker seeker = getAuthenticatedSeeker();
		
		Experience experience = new Experience();
		experience.setProfile(seeker.getProfile());
		experience.setJobTitle(experienceDto.getJobTitle());
		experience.setCompany(experienceDto.getCompany());
		experience.setCity(experienceDto.getCity());
		experience.setDescription(experienceDto.getDescription());
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartDate(experienceDto.getStartDate());
		timePeriod.setEndDate(experienceDto.getEndDate());
		experience.setTimePeriod(timePeriod);

		
		experienceRepository.save(experience);
		
		return seeker.getProfile().getExperiences();
	}
	
	@Override
	public List<Experience> updateExperience(int id, ExperienceDto experienceDto) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Experience experience = experienceRepository.findById(id);
		
		if(!isInformationOwner(seeker, experience))
			throw new AccessDeniedException("You are not the owner of this experience.");
		
		experience.setJobTitle(experienceDto.getJobTitle());
		experience.setCompany(experienceDto.getCompany());
		experience.setCity(experienceDto.getCity());
		experience.setDescription(experienceDto.getDescription());
		TimePeriod timePeriod = experience.getTimePeriod();
		timePeriod.setStartDate(experienceDto.getStartDate());
		timePeriod.setEndDate(experienceDto.getEndDate());

		
		experienceRepository.save(experience);
		
		return seeker.getProfile().getExperiences();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Experience> deleteExperience(int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Experience experience = experienceRepository.findById(id);
		
		if(!isInformationOwner(seeker, experience))
			throw new AccessDeniedException("You are not the owner of this experience.");
		
		seeker.getProfile().removeExperience(experience);
		
		experienceRepository.deleteById(id);
		
		return seeker.getProfile().getExperiences();
	}
	
	
	
	//**********************************EDUCATIONS**********************************
	
	@Transactional
	@Override
	public List<Education> findEducationsById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
			
		return seeker.getProfile().getEducations();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Education> findEducations() {
		Seeker seeker = getAuthenticatedSeeker();
		return seeker.getProfile().getEducations();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Education> addEducation(EducationDto educationDto) {
		Seeker seeker = getAuthenticatedSeeker();
		
		Education education = new Education();
		education.setProfile(seeker.getProfile());
		education.setType(educationDto.getType());
		education.setInstitution(educationDto.getInstitution());
		education.setCity(educationDto.getCity());
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartDate(educationDto.getStartDate());
		timePeriod.setEndDate(educationDto.getEndDate());
		education.setTimePeriod(timePeriod);

		educationRepository.save(education);
		
		return seeker.getProfile().getEducations();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Education> updateEducation(int id, EducationDto educationDto) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Education education = educationRepository.findById(id);
		
		if(!isInformationOwner(seeker, education))
			throw new AccessDeniedException("You are not the owner of this education.");
		
		education.setType(educationDto.getType());
		education.setInstitution(educationDto.getInstitution());
		education.setCity(educationDto.getCity());
		TimePeriod timePeriod = education.getTimePeriod();
		timePeriod.setStartDate(educationDto.getStartDate());
		timePeriod.setEndDate(educationDto.getEndDate());

		educationRepository.save(education);
		
		return seeker.getProfile().getEducations();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Education> deleteEducation(int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Education education = educationRepository.findById(id);
		
		if(!isInformationOwner(seeker, education))
			throw new AccessDeniedException("You are not the owner of this education.");
		
		seeker.getProfile().removeEducation(education);
		
		educationRepository.deleteById(id);
		
		return seeker.getProfile().getEducations();
	}
	
	
	
	//**********************************PROJECTS**********************************
	
	@Transactional
	@Override
	public List<Project> findProjectsById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
			
		return seeker.getProfile().getProjects();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Project> findProjects() {
		Seeker seeker = getAuthenticatedSeeker();
		return seeker.getProfile().getProjects();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Project> addProject(ProjectDto projectDto) {
		Seeker seeker = getAuthenticatedSeeker();
		
		Project project = new Project();
		project.setProfile(seeker.getProfile());
		project.setTitle(projectDto.getTitle());
		project.setDescription(projectDto.getDescription());
		TimePeriod timePeriod = new TimePeriod();
		timePeriod.setStartDate(projectDto.getStartDate());
		timePeriod.setEndDate(projectDto.getEndDate());
		project.setTimePeriod(timePeriod);
		
		projectRepository.save(project);
		
		return seeker.getProfile().getProjects();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Project> updateProject(int id, ProjectDto projectDto) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Project project = projectRepository.findById(id);
		
		if(!isInformationOwner(seeker, project))
			throw new AccessDeniedException("You are not the owner of this project.");
		
		project.setTitle(projectDto.getTitle());
		project.setDescription(projectDto.getDescription());
		TimePeriod timePeriod = project.getTimePeriod();
		timePeriod.setStartDate(projectDto.getStartDate());
		timePeriod.setEndDate(projectDto.getEndDate());
		
		projectRepository.save(project);
		
		return seeker.getProfile().getProjects();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Project> deleteProject(int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Project project = projectRepository.findById(id);
		
		if(!isInformationOwner(seeker, project))
			throw new AccessDeniedException("You are not the owner of this project.");
		
		seeker.getProfile().removeProject(project);
		
		projectRepository.deleteById(id);
		
		return seeker.getProfile().getProjects();
	}
	
	
	
	//**********************************SKILLS**********************************
	
	@Transactional
	@Override
	public List<Skill> findSkillsById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
			
		return seeker.getProfile().getSkills();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Skill> findSkills() {
		Seeker seeker = getAuthenticatedSeeker();
		return seeker.getProfile().getSkills();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Skill> addSkill(NameDto skillDto) {
		Seeker seeker = getAuthenticatedSeeker();
		
		Skill skill = new Skill();
		skill.setProfile(seeker.getProfile());
		skill.setName(skillDto.getName());
		
		skillRepository.save(skill);
		
		return seeker.getProfile().getSkills();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Skill> updateSkill(int id, NameDto skillDto) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Skill skill = skillRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		skill.setName(skillDto.getName());
		
		skillRepository.save(skill);
		
		return seeker.getProfile().getSkills();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Skill> deleteSkill(int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Skill skill = skillRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		seeker.getProfile().removeSkill(skill);
		
		skillRepository.deleteById(id);
		
		return seeker.getProfile().getSkills();
	}
	
	
	
	//**********************************TECHNOLOGIES**********************************
	
	@Transactional
	@Override
	public List<Technology> findTechnologiesById(int id, int skillId) throws NotFoundException, AccessDeniedException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
		
		
		Skill skill = skillRepository.findById(skillId);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("That seeker is not the owner of this skill.");
		
		return skill.getTechnologies();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> findTechnologies(int skillId) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		return skill.getTechnologies();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> addTechnology(NameDto technologyDto, int skillId) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		Technology technology = new Technology();
		technology.setSkill(skill);
		technology.setName(technologyDto.getName());
		
		technologyRepository.save(technology);
		
		return skill.getTechnologies();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> updateTechnology(NameDto technologyDto, int skillId, int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		Technology technology = technologyRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		if(!isTechnologyOwner(skill, technology))
			throw new AccessDeniedException("This technology does not belong to this skill.");
		
		technology.setName(technologyDto.getName());
		
		technologyRepository.save(technology);
		
		return skill.getTechnologies();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> deleteTechnology(int skillId, int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		Technology technology = technologyRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new AccessDeniedException("You are not the owner of this skill.");
		
		if(!isTechnologyOwner(skill, technology))
			throw new AccessDeniedException("This technology does not belong to this skill.");
		
		skill.removeTechnology(technology);
		technologyRepository.deleteById(id);
		
		return skill.getTechnologies();
	}
	
	
	
	//**********************************LANGUAGES**********************************
	
	@Transactional
	@Override
	public List<Language> findLanguagesById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Seeker seeker = null;
		if(user instanceof Seeker)
			seeker = (Seeker)user;
		else
			throw new NotFoundException("There is no seeker with that id.");
			
		return seeker.getProfile().getLanguages();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Language> findLanguages() {
		Seeker seeker = getAuthenticatedSeeker();
		return seeker.getProfile().getLanguages();
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Language> addLanguage(LanguageDto languageDto) {
		Seeker seeker = getAuthenticatedSeeker();
		
		Language language = new Language();
		language.setName(languageDto.getName());
		language.setLevel(languageDto.getLevel());
		language.setProfile(seeker.getProfile());
		
		languageRepository.save(language);
		
		return seeker.getProfile().getLanguages();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Language> updateLanguage(int id, LanguageDto languageDto) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Language language = languageRepository.findById(id);
		
		if(!isInformationOwner(seeker, language))
			throw new AccessDeniedException("You are not the owner of this language.");
		
		
		language.setName(languageDto.getName());
		language.setLevel(languageDto.getLevel());
		
		languageRepository.save(language);
		
		return seeker.getProfile().getLanguages();
	}
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Language> deleteLanguage(int id) throws AccessDeniedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Language language = languageRepository.findById(id);
		
		if(!isInformationOwner(seeker, language))
			throw new AccessDeniedException("You are not the owner of this language.");
		
		languageRepository.deleteById(id);
		
		return seeker.getProfile().getLanguages();
	}
	
	
	

	private List<OfferResponse> mapToResponse(Set<Offer> offers) {
		List<OfferResponse> response = new ArrayList<>();
		for(Offer offer : offers) {
			OfferResponse item = new OfferResponse();
			item.setId(offer.getId());
			item.setTitle(offer.getTitle());
			item.setDescription(offer.getDescription());
			item.setDate(offer.getDate());
			item.setCity(offer.getCity().getName());
			item.setDomain(offer.getDomain().getName());
			item.setCompanyName(offer.getCompany().getName());
			if(offer instanceof InternshipOffer) {
				InternshipOffer internshipOffer = (InternshipOffer) offer;
				item.setInternshipOffer(true);
				item.setType(internshipOffer.getInternshipType().getName());
				item.setDuration(internshipOffer.getDuration().getDuration());
			}
			else if(offer instanceof JobOffer) {
				JobOffer jobOffer = (JobOffer) offer;
				item.setInternshipOffer(false);
				item.setType(jobOffer.getJobType().getName());
				item.setDuration(null);
			}
				
			response.add(item);
		}
		return response;
	}

	
	
	
	
	//**********************************PRIVATE METHODS**********************************

	private Seeker getAuthenticatedSeeker() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
	}

	
	private boolean isInformationOwner(Seeker seeker, Object information) {
		boolean isOwner = false;
		
		if(information instanceof Experience)
			for(Experience experience : seeker.getProfile().getExperiences())
				if(experience == information)
					isOwner = true;
		
		if(information instanceof Education)
			for(Education education : seeker.getProfile().getEducations())
				if(education == information)
					isOwner = true;
		
		if(information instanceof Project)
			for(Project project : seeker.getProfile().getProjects())
				if(project == information)
					isOwner = true;
		
		if(information instanceof Skill)
			for(Skill skill : seeker.getProfile().getSkills())
				if(skill == information)
					isOwner = true;
	
		
		if(information instanceof Language)
			for(Language language : seeker.getProfile().getLanguages())
				if(language == information)
					isOwner = true;


		return isOwner;
	}
	
	
	private boolean isTechnologyOwner(Skill skill, Technology technology) {
		boolean isOwner = false;
		
		for(Technology technologyIteration : skill.getTechnologies())
			if(technologyIteration == technology)
				isOwner = true;
		
		return isOwner;
	}



}
