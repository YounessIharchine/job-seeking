package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.ParagraphDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;

public interface CompanyService {
	
	//**********************************FIND COMPANIES**********************************

	List<FindCompanyResponse> findCompanies(String name, String domain) throws IOException;

	
	//**********************************PUBLIC PROFILE**********************************

	CompanyResponse findCompany(int id) throws IOException, NotFoundException;

	
	//**********************************OWN PROFILE**********************************

	void updateInfo(Map<String, String> map) throws IOException;
	
	
	//**********************************PARAGRAPHS**********************************

	List<Paragraph> findParagraphs();

	List<Paragraph> addParagraph(ParagraphDto paragraphDto);

	List<Paragraph> deleteParagraph(int id) throws AccessDeniedException;

	
	//**********************************PHOTOS**********************************

	void addPhoto(PhotoDto photoDto) throws IOException;

	Set<Photo> findPhotos();

	void deletePhoto(int id) throws AccessDeniedException;


}
