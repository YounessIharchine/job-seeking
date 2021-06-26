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
import com.pfa.jobseeking.rest.response.CompanyResponse;

public interface CompanyService {
	
	CompanyResponse findCompany(int id);

	void updateInfo(Map<String, String> map) throws IOException;
	
	List<Paragraph> findParagraphs();

	List<Paragraph> addParagraph(ParagraphDto paragraphDto);

	List<Paragraph> deleteParagraph(int id) throws AccessDeniedException;

	void addPhoto(PhotoDto photoDto) throws IOException;

	Set<Photo> findPhotos();

	void deletePhoto(int id) throws AccessDeniedException;

}
