package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
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

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.ParagraphRepository;
import com.pfa.jobseeking.repository.PhotoRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.dto.TextDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.response.CompanyResponse;
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
	
	
	@Override
	public CompanyResponse findCompany(int id) {
		CompanyResponse response = new CompanyResponse();
		Company company = (Company)userRepository.findById(id);
		
		response.setName(company.getName());
		response.setPublicEmail(company.getPublicEmail());
		response.setPhone(company.getPhone());
		if(company.getCity() != null)
			response.setCity(company.getCity().getName());
		else 
			response.setCity(null);
		if(company.getDomain() != null)
			response.setDomain(company.getDomain().getName());
		else
			response.setDomain(null);
		response.setLogo(null);
		response.setCoverPhoto(null);
		response.setWebSite(company.getCompanyProfile().getWebSite());
		
		return response;
	}
	

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void updateInfo(Map<String, String> map) throws IOException {
		Company company = getAuthenticatedCompany();
		
		if(map.containsKey("name"))
			company.setName(map.get("name"));
		if(map.containsKey("city"))
			company.setCity(cityRepository.findCityByName(map.get("city")));
		if(map.containsKey("domain"))
			company.setDomain(domainRepository.findDomainByName(map.get("domain")));
		if(map.containsKey("document")) {
			String documentPath = "\\documents\\document-" + company.getId() + ".pdf";
			byte[] documentBytes = Base64.getDecoder().decode(map.get("document"));
			FileUtils.writeByteArrayToFile(new File(path+documentPath), documentBytes);
			company.setDocumentPath(documentPath.replace("\\", "\\\\"));
		}
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
		Company company = getAuthenticatedCompany();
		
		Paragraph paragraph = new Paragraph(textDto.getText());
		paragraph.setCompanyProfile(company.getCompanyProfile());
		company.getCompanyProfile().addParagraph(paragraph);
		
		userRepository.save(company); // this is mandatory
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<Paragraph> findParagraphs() {
		Company company = getAuthenticatedCompany();
		
		return company.getCompanyProfile().getParagraphs();
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void deleteParagraph(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Paragraph paragraph = paragraphRepository.findById(id);
		
		
		if(!isParagraphOwner(company, paragraph))
			throw new AccessDeniedException("You don't own this paragraph.");
		
		
		paragraphRepository.deleteById(id);
	}

	@Override
	public void addPhoto(PhotoDto photoDto) throws IOException {
		Company company = getAuthenticatedCompany();
		
		
		
		//this gymnastics is to get the id for storage name
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
	public void deletePhoto(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Photo photo = photoRepository.findById(id);
		
		
		if(!isPhotoOwner(company, photo))
			throw new AccessDeniedException("You don't own this photo.");
		
		
		photoRepository.deleteById(id);
	}

	
	
	
	
	
	private Company getAuthenticatedCompany() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Company)userRepository.findUserByEmail(authenticatedUserEmail);
	}
	
	
	private boolean isParagraphOwner(Company company, Paragraph paragraph) {
		boolean isOwner = false;
		
		for(Paragraph iteratedParagraph : company.getCompanyProfile().getParagraphs())
			if(iteratedParagraph == paragraph)
				isOwner = true;
		
		return isOwner;
	}
	
	private boolean isPhotoOwner(Company company, Photo photo) {
		boolean isOwner = false;
		
		for(Photo iteratedPhoto : company.getCompanyProfile().getPhotos())
			if(iteratedPhoto == photo)
				isOwner = true;
		
		return isOwner;
	}

}
