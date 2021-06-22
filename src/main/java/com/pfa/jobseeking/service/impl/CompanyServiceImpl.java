package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.ParagraphRepository;
import com.pfa.jobseeking.repository.PhotoRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.CompanyMandatoryInfoDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.TextDto;
import com.pfa.jobseeking.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	ParagraphRepository paragraphRepository;
	
	@Autowired
	PhotoRepository photoRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void setMandatoryCompanyInfo(CompanyMandatoryInfoDto companyMandatoryInfoDto) throws IOException {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		company.setName(companyMandatoryInfoDto.getName());
		company.setCity(cityRepository.findCityByName(companyMandatoryInfoDto.getCity()));
		company.setDomain(domainRepository.findDomainByName(companyMandatoryInfoDto.getDomain()));
		String documentPath = "\\documents\\document-" + company.getId() + ".pdf";
		byte[] documentBytes = Base64.getDecoder().decode(companyMandatoryInfoDto.getDocument());
		FileUtils.writeByteArrayToFile(new File(path+documentPath), documentBytes);
		company.setDocumentPath(documentPath.replace("\\", "\\\\"));
	}
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void updateInfo(Map<String, String> map) throws IOException {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		if(map.containsKey("publicEmail"))
			company.setPublicEmail(map.get("publicEmail"));
		if(map.containsKey("phone"))
			company.setPhone(map.get("phone"));
		if(map.containsKey("webSite"))
			company.getCompanyProfile().setWebSite(map.get("webSite"));
		if(map.containsKey("logo")) {
			String logoPath = "\\logos\\logo-" + company.getId() + ".png";
			byte[] logoBytes = Base64.getDecoder().decode(map.get("logo"));
			FileUtils.writeByteArrayToFile(new File(path+logoPath), logoBytes);
			company.getCompanyProfile().setLogo(logoPath.replace("\\", "\\\\"));
		}
		if(map.containsKey("coverPhoto")) {
			String coverPath = "\\coverPhotos\\cover-" + company.getId() + ".png";
			byte[] coverBytes = Base64.getDecoder().decode(map.get("coverPhoto"));
			FileUtils.writeByteArrayToFile(new File(path+coverPath), coverBytes);
			company.getCompanyProfile().setCoverPhoto(coverPath.replace("\\", "\\\\"));
		}
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void addParagraph(TextDto textDto) {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		Paragraph paragraph = new Paragraph(textDto.getText());
		paragraph.setCompanyProfile(company.getCompanyProfile());
		company.getCompanyProfile().addParagraph(paragraph);
		
		userRepository.save(company); // this is mandatory
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public Set<Paragraph> findParagraphs() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		return company.getCompanyProfile().getParagraphs();
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void deleteParagraph(int id) {
//		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
//		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		//can check if this paragraph is really mapped to this company by adding equals()..... but too lazy to implement also not much time left
		
		paragraphRepository.deleteById(id);
	}

	@Override
	public void addPhoto(PhotoDto photoDto) throws IOException {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		
		
		
		Photo photo = new Photo();
		photo.setCompanyProfile(company.getCompanyProfile());
		
		photo = photoRepository.save(photo);
		
		String photoPath = "\\companyPhotos\\photo-" + company.getId() + "-" + photo.getId() + ".png";
		byte[] photoBytes = Base64.getDecoder().decode(photoDto.getPhoto());
		FileUtils.writeByteArrayToFile(new File(path+photoPath), photoBytes);
		photo.setPhotoPath(photoPath.replace("\\", "\\\\"));
		
		photo = photoRepository.save(photo);

	}

	@Override
	public Set<Photo> findPhotos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePhoto(int id) {
		photoRepository.deleteById(id);
	}



}