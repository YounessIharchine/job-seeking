package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
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
import com.pfa.jobseeking.model.user.Seeker;
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
import com.pfa.jobseeking.rest.dto.ProjectDto;
import com.pfa.jobseeking.rest.exception.UnauthorizedException;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerAccountResponse;
import com.pfa.jobseeking.rest.response.SeekerProfileResponse;
import com.pfa.jobseeking.rest.response.SeekerStepOneResponse;
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
	
	
	@Override
	public SeekerProfileResponse findSeeker(int id) {
		SeekerProfileResponse response = new SeekerProfileResponse();
		Seeker seeker = (Seeker)userRepository.findById(id);
		
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
		response.setCv(null);
		response.setPhoto(null);
		response.setSpeciality(seeker.getProfile().getSpeciality());
		response.setDescription(seeker.getProfile().getDescription());
		response.setPortefolio(seeker.getProfile().getPortefolio());
		response.setGithub(seeker.getProfile().getGithub());

		return response;
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public SeekerAccountResponse fetchSeekerAccount() {
		SeekerAccountResponse response = new SeekerAccountResponse();
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
	public SeekerStepOneResponse fetchSeekerStepOne() {
		SeekerStepOneResponse response = new SeekerStepOneResponse();
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
	public void updateInfo(Map<String, String> map) throws IOException {
		Seeker seeker = getAuthenticatedSeeker();
		
		if(map.containsKey("firstName"))
			seeker.setFirstName(map.get("firstName"));
		if(map.containsKey("lastName"))
			seeker.setLastName(map.get("lastName"));
		if(map.containsKey("phone"))
			seeker.setPhone(map.get("phone"));
		if(map.containsKey("address"))
			seeker.setAddress(map.get("address"));
		if(map.containsKey("birthDate"))
			seeker.setBirthDate(map.get("birthDate"));
		if(map.containsKey("city"))
			seeker.setCity(cityRepository.findCityByName(map.get("city")));
//		if(map.containsKey("cv"))
//		seeker.setFirstName(map.get("cv"));
		if(map.containsKey("photo")) {
			String photoPath = "\\profilePhotos\\photo-" + seeker.getId() + ".png";
			byte[] imageBytes = Base64.getDecoder().decode(map.get("photo"));
			FileUtils.writeByteArrayToFile(new File(path+photoPath), imageBytes);
			seeker.getProfile().setPhoto(photoPath.replace("\\", "\\\\"));
		}
		if(map.containsKey("speciality"))
			seeker.getProfile().setSpeciality(map.get("speciality"));
		if(map.containsKey("github"))
			seeker.getProfile().setGithub(map.get("github"));
		if(map.containsKey("portfolio"))
			seeker.getProfile().setPortefolio(map.get("portfolio"));
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
	
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void follow(String companyName) {
		Seeker seeker = getAuthenticatedSeeker();
		seeker.followCompany(companyRepository.findCompanyByName(companyName));
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public void unfollow(String companyName) {
		Seeker seeker = getAuthenticatedSeeker();
		seeker.unfollowCompany(companyRepository.findCompanyByName(companyName));
	}

	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Override
	public List<OfferResponse> findSavedOffers() {
		Seeker seeker = getAuthenticatedSeeker();

		return mapToResponse(seeker.getOffers());
	}
	
	
	//*****************EXPERIENCES*****************
	
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

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Experience> deleteExperience(int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		Experience experience = experienceRepository.findById(id);
		
		if(!isInformationOwner(seeker, experience))
			throw new UnauthorizedException("You are not the owner of this experience.");
		
		experienceRepository.deleteById(id);
		
		return seeker.getProfile().getExperiences();
	}
	
	
	
	//*****************EDUCATIONS*****************
	
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
	public List<Education> deleteEducation(int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Education education = educationRepository.findById(id);
		
		if(!isInformationOwner(seeker, education))
			throw new UnauthorizedException("You are not the owner of this education.");
		
		educationRepository.deleteById(id);
		
		return seeker.getProfile().getEducations();
	}
	
	
	
	//*****************PROJECTS*****************
	
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
	public List<Project> deleteProject(int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Project project = projectRepository.findById(id);
		
		if(!isInformationOwner(seeker, project))
			throw new UnauthorizedException("You are not the owner of this project.");
		
		projectRepository.deleteById(id);
		
		return seeker.getProfile().getProjects();
	}
	
	
	
	//*****************SKILLS*****************
	
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
	public List<Skill> deleteSkill(int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Skill skill = skillRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new UnauthorizedException("You are not the owner of this skill.");
		
		skillRepository.deleteById(id);
		
		return seeker.getProfile().getSkills();
	}
	
	
	
	//*****************SKILLS*****************
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> findTechnologies(int skillId) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		
		if(!isInformationOwner(seeker, skill))
			throw new UnauthorizedException("You are not the owner of this skill.");
		
		return skill.getTechnologies();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> addTechnology(NameDto technologyDto, int skillId) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		
		if(!isInformationOwner(seeker, skill))
			throw new UnauthorizedException("You are not the owner of this skill.");
		
		Technology technology = new Technology();
		technology.setSkill(skill);
		technology.setName(technologyDto.getName());
		
		technologyRepository.save(technology);
		
		return skill.getTechnologies();
	}

	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Technology> deleteTechnology(int skillId, int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		Skill skill = skillRepository.findById(skillId);
		Technology technology = technologyRepository.findById(id);
		
		if(!isInformationOwner(seeker, skill))
			throw new UnauthorizedException("You are not the owner of this skill.");
		
		if(!isTechnologyOwner(skill, technology))
			throw new UnauthorizedException("This technology does not belong to this skill.");
		
		skill.removeTechnology(technology);
		technologyRepository.deleteById(id);
		
		return skill.getTechnologies();
	}
	
	
	
	//*****************LANGUAGES*****************
	
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
//		seeker.getProfile().addLanguage(language);
		
		return seeker.getProfile().getLanguages();
	}
	
	
	@PreAuthorize("hasRole('ROLE_SEEKER')")
	@Transactional
	@Override
	public List<Language> deleteLanguage(int id) throws UnauthorizedException {
		Seeker seeker = getAuthenticatedSeeker();
		
		Language language = languageRepository.findById(id);
		
		if(!isInformationOwner(seeker, language))
			throw new UnauthorizedException("You are not the owner of this language.");
		
		languageRepository.deleteById(id);
//		seeker.getProfile().removeLanguage(languageRepository.findById(id));
//		userRepository.save(seeker);
		
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


	private Seeker getAuthenticatedSeeker() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
	}
//	
//	private boolean isSkillOwner(Seeker seeker, Skill skill) {
//		boolean isOwner = false;
//		
//		for(Skill skillIteration : seeker.getProfile().getSkills())
//			if(skillIteration == skill)
//				isOwner = true;
//		
//		return isOwner;
//	}

	
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
