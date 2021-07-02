package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.List;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.rest.dto.ParagraphDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CompanyPhotoResponse;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;

public interface CompanyService {
	
	//**********************************FIND COMPANIES**********************************

	List<FindCompanyResponse> findCompanies(String name, String domain) throws IOException;

	
	//**********************************PUBLIC PROFILE**********************************

	CompanyResponse findCompany(int id) throws IOException, NotFoundException;

	PhotoDto findCompanyLogo(int id) throws IOException, NotFoundException;

	
	//**********************************OWN PROFILE**********************************

	CompanyResponse updateInfo(CompanyResponse companyResponse) throws IOException;
	
	PhotoDto updateLogo(PhotoDto photoDto) throws IOException;

	
	
	//**********************************PARAGRAPHS**********************************

	List<Paragraph> findParagraphs(int id) throws NotFoundException;

	List<Paragraph> addParagraph(ParagraphDto paragraphDto);
	
	List<Paragraph> updateParagraph(int id, ParagraphDto paragraphDto) throws AccessDeniedException;

	List<Paragraph> deleteParagraph(int id) throws AccessDeniedException;

	
	//**********************************PHOTOS**********************************

	List<CompanyPhotoResponse> findPhotos(int id) throws NotFoundException, IOException;
	
	List<CompanyPhotoResponse> addPhoto(PhotoDto photoDto) throws IOException;

	List<CompanyPhotoResponse> deletePhoto(int id) throws AccessDeniedException, IOException;

}
