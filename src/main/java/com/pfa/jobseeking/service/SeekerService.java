package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pfa.jobseeking.model.seeker.Education;
import com.pfa.jobseeking.model.seeker.Experience;
import com.pfa.jobseeking.model.seeker.Language;
import com.pfa.jobseeking.rest.dto.EducationDto;
import com.pfa.jobseeking.rest.dto.ExperienceDto;
import com.pfa.jobseeking.rest.dto.LanguageDto;
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

	SeekerProfileResponse findSeeker(int id);

	SeekerAccountResponse fetchSeekerAccount();

	SeekerStepOneResponse fetchSeekerStepOne();
	
	
	//*****************EXPERIENCES*****************
	
	List<Experience> findExperiences();

	List<Experience> addExperience(ExperienceDto experienceDto);

	List<Experience> deleteExperience(int id);
	
	
	//*****************EXPERIENCES*****************

	List<Education> findEducations();

	List<Education> addEducation(EducationDto educationDto);

	List<Education> deleteEducation(int id);
	
	
	//*****************LANGUAGES*****************
	
	List<Language> findLanguages();

	List<Language> addLanguage(LanguageDto languageDto);

	List<Language> deleteLanguage(int id);


}
