package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.Offer;
import com.pfa.jobseeking.model.seeker.Follow;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.CompanyRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.ParagraphRepository;
import com.pfa.jobseeking.repository.PhotoRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.ParagraphDto;
import com.pfa.jobseeking.rest.dto.PhotoDto;
import com.pfa.jobseeking.rest.exception.AccessDeniedException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.response.CompanyPhotoResponse;
import com.pfa.jobseeking.rest.response.CompanyResponse;
import com.pfa.jobseeking.rest.response.FindCompanyResponse;
import com.pfa.jobseeking.rest.response.OfferResponse;
import com.pfa.jobseeking.rest.response.SeekerResponse;
import com.pfa.jobseeking.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
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
		
		response.setId(company.getId());
		response.setName(company.getName());
		response.setPublicEmail(company.getPublicEmail());
		response.setPhone(company.getPhone());
		response.setCity(company.getCity().getName());
		response.setDomain(company.getDomain().getName());
		response.setWebSite(company.getCompanyProfile().getWebSite());
		response.setFollowed(isFollowed);
		
		return response;
	}
	
	@Override
	public PhotoDto findCompanyLogo(int id) throws IOException, NotFoundException {
		PhotoDto response = new PhotoDto();
		
		User user = userRepository.findById(id);
		Company company = null;
		if(user instanceof Company)
			company = (Company)user;
		else
			throw new NotFoundException("There is no company with that id.");
		
		company = (Company)user;
		
		byte[] logoBytes = FileUtils.readFileToByteArray(new File(path+company.getCompanyProfile().getLogo()));
		response.setPhoto(Base64.getEncoder().encodeToString(logoBytes));
		
		return response;
	}
	
	
	//**********************************OWN PROFILE**********************************

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public CompanyResponse updateInfo(CompanyResponse companyDto) throws IOException {
		Company company = getAuthenticatedCompany();
		
		companyDto.setId(company.getId());
		company.setName(companyDto.getName());
		company.setPublicEmail(companyDto.getPublicEmail());
		company.setPhone(companyDto.getPhone());
		company.setCity(cityRepository.findCityByName(companyDto.getCity()));
		company.setDomain(domainRepository.findDomainByName(companyDto.getDomain()));
		company.getCompanyProfile().setWebSite(companyDto.getWebSite());
		
		return companyDto;
	}

	
	@Override
	public PhotoDto updateLogo(PhotoDto photoDto) throws IOException {
		Company company = getAuthenticatedCompany();
		
		byte[] logoBytes = Base64.getDecoder().decode(photoDto.getPhoto());
		FileUtils.writeByteArrayToFile(new File(path+company.getCompanyProfile().getLogo()), logoBytes);
		
		return photoDto;
	}

	
	
	//**********************************FOLLOWERS**********************************
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<SeekerResponse> findFollowers() throws IOException {
		Company company = getAuthenticatedCompany();
		List<SeekerResponse> response = new ArrayList<>();
		
		for(Follow follow : company.getFollows()) {
			SeekerResponse item = new SeekerResponse();
			item.setId(follow.getSeeker().getId());
			item.setName(follow.getSeeker().getFirstName()+" "+follow.getSeeker().getLastName());
			item.setSpeciality(follow.getSeeker().getProfile().getSpeciality());
			item.setCity(follow.getSeeker().getCity().getName());
			byte[] photoBytes = FileUtils.readFileToByteArray(new File(path+follow.getSeeker().getProfile().getPhoto()));
			item.setPhoto(Base64.getEncoder().encodeToString(photoBytes));
			response.add(item);
		}
		
		return response;
	}
	
	
	
	//**********************************OFFERS**********************************

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<OfferResponse> findOffers() {
		Company company = getAuthenticatedCompany();

		return mapToOfferResponse(company.getOffers());
	}
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<OfferResponse> openOffer(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Offer offer = offerRepository.findById(id);
		
		boolean isOfferOwner = false;
		
		for(Offer iteratedOffer : company.getOffers())
			if(iteratedOffer == offer)
				isOfferOwner = true;
		
		if(!isOfferOwner)
			throw new AccessDeniedException("You are not the owner of this offer.");
		
		offer.setOpen(true);
		
		return mapToOfferResponse(company.getOffers());
	}
	

	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<OfferResponse> closeOffer(int id) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		
		Offer offer = offerRepository.findById(id);
		
		boolean isOfferOwner = false;
		
		for(Offer iteratedOffer : company.getOffers())
			if(iteratedOffer == offer)
				isOfferOwner = true;
		
		if(!isOfferOwner)
			throw new AccessDeniedException("You are not the owner of this offer.");
		
		offer.setOpen(false);

		return mapToOfferResponse(company.getOffers());
	}
	
	//**********************************PARAGRAPHS**********************************

	@Transactional
	@Override
	public List<Paragraph> findParagraphs(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		Company company = null;
		if(user instanceof Company)
			company = (Company)user;
		else
			throw new NotFoundException("There is no company with that id.");
		
		company = (Company)user;
		
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
	
	
	@Override
	public List<Paragraph> updateParagraph(int id, ParagraphDto paragraphDto) throws AccessDeniedException {
		Company company = getAuthenticatedCompany();
		Paragraph paragraph = paragraphRepository.findById(id);
		
		if(!isParagraphOwner(company, paragraph))
			throw new AccessDeniedException("You don't own this paragraph.");
		
		paragraph.setTitle(paragraphDto.getTitle());
		paragraph.setText(paragraphDto.getText());
		
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

	@Transactional
	@Override
	public List<CompanyPhotoResponse> findPhotos(int id) throws NotFoundException, IOException {
		User user = userRepository.findById(id);
		Company company = null;
		if(user instanceof Company)
			company = (Company)user;
		else
			throw new NotFoundException("There is no company with that id.");
		
		company = (Company)user;
		
		List<CompanyPhotoResponse> response = mapToPhotosResponse(company.getCompanyProfile().getPhotos());
		return response;
	}
	
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<CompanyPhotoResponse> addPhoto(PhotoDto photoDto) throws IOException {
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
		List<CompanyPhotoResponse> response = mapToPhotosResponse(company.getCompanyProfile().getPhotos());
		return response;
	}
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public List<CompanyPhotoResponse> deletePhoto(int id) throws AccessDeniedException, IOException {
		Company company = getAuthenticatedCompany();
		Photo photo = photoRepository.findById(id);
		
		
		if(!isPhotoOwner(company, photo))
			throw new AccessDeniedException("You don't own this photo.");
		
		company.getCompanyProfile().removePhoto(photo);
		
		photoRepository.deleteById(id);
		List<CompanyPhotoResponse> response = mapToPhotosResponse(company.getCompanyProfile().getPhotos());
		return response;
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
			if(company.getCompanyProfile().getLogo() != null) {
				byte[] logoBytes = FileUtils.readFileToByteArray(new File(path+company.getCompanyProfile().getLogo()));
				item.setLogo(Base64.getEncoder().encodeToString(logoBytes));
			}
			response.add(item);
		}
		
		return response;
	}
	
	private List<CompanyPhotoResponse> mapToPhotosResponse(List<Photo> photos) throws IOException {
		List<CompanyPhotoResponse> response = new ArrayList<>();
		for(Photo photo : photos) {
			CompanyPhotoResponse item = new CompanyPhotoResponse();
			item.setId(photo.getId());
			byte[] photoBytes = FileUtils.readFileToByteArray(new File(path+photo.getPhotoPath()));
			item.setPhoto(Base64.getEncoder().encodeToString(photoBytes));
			response.add(item);
		}
		return response;
	}
	
	
	private List<OfferResponse> mapToOfferResponse(Set<Offer> offers) {
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
			item.setOpen(offer.isOpen());
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
