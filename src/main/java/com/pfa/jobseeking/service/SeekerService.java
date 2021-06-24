package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pfa.jobseeking.model.seeker.Language;
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

	Set<Language> findLanguages();

	Set<Language> addLanguage(LanguageDto languageDto);

	Set<Language> deleteLanguage(int id);

}
