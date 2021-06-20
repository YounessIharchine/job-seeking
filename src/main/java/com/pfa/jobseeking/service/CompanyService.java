package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.rest.dto.CompanyMandatoryInfoDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.TextDto;

public interface CompanyService {

	void updateInfo(Map<String, String> map) throws IOException;

	void setMandatoryCompanyInfo(CompanyMandatoryInfoDto companyMandatoryInfoDto) throws IOException;

	void addParagraph(TextDto textDto);

	Set<Paragraph> findParagraphs();

	void deleteParagraph(int id);

	void addPhoto(PhotoDto photoDto) throws IOException;

	Set<Photo> findPhotos();

	void deletePhoto(int id);

}
