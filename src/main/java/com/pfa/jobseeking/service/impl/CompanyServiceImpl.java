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

import com.pfa.jobseeking.model.company.Paragraph;
import com.pfa.jobseeking.model.company.Photo;
import com.pfa.jobseeking.model.seeker.Follow;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.CompanyRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.ParagraphRepository;
import com.pfa.jobseeking.repository.PhotoRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.ParagraphDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;
import com.pfa.jobseeking.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
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
	
	
	
	//**********************************FIND COMPANIES**********************************

	@Override
	public List<FindCompanyResponse> findCompanies(String name, String domain) throws IOException {
		if(name == null || name.isEmpty())
			if(domain == null || domain.isEmpty())
				return mapToResponse(companyRepository.findAll());
			else
				return mapToResponse(companyRepository.findAllByDomain(domain));
		else
			if(domain == null || domain.isEmpty())
				return mapToResponse(companyRepository.findAllByName(name));
			else
				return mapToResponse(companyRepository.findAllByNameAndDomain(name, domain));
	}
	
	
	
	
	//**********************************PUBLIC PROFILE**********************************

	@Override
	public CompanyResponse findCompany(int id) throws IOException, NotFoundException {
		CompanyResponse response = new CompanyResponse();

		User user = userRepository.findById(id);
		Company company = null;
		if(user instanceof Company)
			company = (Company)user;
		else
			throw new NotFoundException("There is no company with that id.");
		
		company = (Company)user;
		
		Seeker seeker = getAuthenticatedSeeker();
		boolean isFollowed = false;
		
		if(seeker != null) 
			for(Follow follow : seeker.getFollows())
				if(follow.getCompany() == company)
					isFollowed = true;
		
		String logo;
		if(company.getCompanyProfile().getLogo() == null)
			logo = null;
		else {
			byte[] logoBytes = FileUtils.readFileToByteArray(new File(path+company.getCompanyProfile().getLogo()));
			logo = Base64.getEncoder().encodeToString(logoBytes);
		}
		
		String coverPhoto;
		if(company.getCompanyProfile().getCoverPhoto() == null)
			coverPhoto = null;
		else {
			byte[] coverPhotoBytes = FileUtils.readFileToByteArray(new File(path+company.getCompanyProfile().getCoverPhoto()));
			coverPhoto = Base64.getEncoder().encodeToString(coverPhotoBytes);
		}
		
		response.setId(company.getId());
		response.setName(company.getName());
		response.setPublicEmail(company.getPublicEmail());
		response.setPhone(company.getPhone());
		response.setCity(company.getCity().getName());
		response.setDomain(company.getDomain().getName());
		response.setLogo(logo);
		response.setCoverPhoto(coverPhoto);
		response.setWebSite(company.getCompanyProfile().getWebSite());
		response.setFollowed(isFollowed);
		
		return response;
	}
	

	
	
	//**********************************OWN PROFILE**********************************

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

	
	
	
	
	//**********************************PARAGRAPHS**********************************

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
	public List<Paragraph> addParagraph(ParagraphDto paragraphDto) {
		Company company = getAuthenticatedCompany();
		
		Paragraph paragraph = new Paragraph();
		paragraph.setTitle(paragraphDto.getTitle());
		paragraph.setText(paragraphDto.getText());
		paragraph.setCompanyProfile(company.getCompanyProfile());
		
		paragraphRepository.save(paragraph);
		
		
		return company.getCompanyProfile().getParagraphs();
		
	}

	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<Paragraph> deleteParagraph(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Paragraph paragraph = paragraphRepository.findById(id);
		
		
		if(!isParagraphOwner(company, paragraph))
			throw new AccessDeniedException("You don't own this paragraph.");
		
		
		company.getCompanyProfile().removeParagraph(paragraph);
		paragraphRepository.deleteById(id);
		
		
		return company.getCompanyProfile().getParagraphs();
	}

	
	
	
	
	//**********************************PHOTOS**********************************

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public Set<Photo> findPhotos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
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
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void deletePhoto(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Photo photo = photoRepository.findById(id);
		
		
		if(!isPhotoOwner(company, photo))
			throw new AccessDeniedException("You don't own this photo.");
		
		company.getCompanyProfile().removePhoto(photo);
		
		photoRepository.deleteById(id);
	}

	
	
	
	
	//**********************************PRIVATE METHODS**********************************

	private Company getAuthenticatedCompany() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Company)userRepository.findUserByEmail(authenticatedUserEmail);
	}
	
	private Seeker getAuthenticatedSeeker() {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return (Seeker)userRepository.findUserByEmail(authenticatedUserEmail);
	}
	
	private List<FindCompanyResponse> mapToResponse(List<Company> companies) throws IOException {
		List<FindCompanyResponse> response = new ArrayList<>();
		
		for(Company company : companies) {
			FindCompanyResponse item = new FindCompanyResponse();
			item.setId(company.getId());
			item.setName(company.getName());
			item.setCity(company.getCity().getName());
			item.setDomain(company.getDomain().getName());
			byte[] logoBytes = FileUtils.readFileToByteArray(new File(path+company.getCompanyProfile().getLogo()));
			item.setLogo(Base64.getEncoder().encodeToString(logoBytes));
			response.add(item);
		}
		
		return response;
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
