package com.pfa.jobseeking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.company.CompanyProfile;
import com.pfa.jobseeking.model.offer.Duration;
import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.model.seeker.Profile;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.DurationRepository;
import com.pfa.jobseeking.repository.InternshipTypeRepository;
import com.pfa.jobseeking.repository.JobTypeRepository;
import com.pfa.jobseeking.repository.OfferRepository;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.CityService;
import com.pfa.jobseeking.service.DomainService;
import com.pfa.jobseeking.service.FillService;

@Service
public class FillServiceImpl implements FillService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	DomainService domainService;
	
	@Autowired
	InternshipTypeRepository internshipTypeRepository;
	
	@Autowired
	JobTypeRepository jobTypeRepository;
	
	@Autowired
	DurationRepository durationRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public void fill() throws NotFoundException, AlreadyExistsException {
		Role seekerRole = new Role("ROLE_SEEKER");
		Role companyRole = new Role("ROLE_COMPANY");
		Role adminRole = new Role("ROLE_ADMIN");
		roleRepository.save(seekerRole);
		roleRepository.save(companyRole);
		roleRepository.save(adminRole);
		
		City city1 = new City("Agadir");
		City city2 = new City("Marrakech");
		City city3 = new City("Casablanca");
		City city4 = new City("Rabat");
		cityService.save(city1);
		cityService.save(city2);
		cityService.save(city3);
		cityService.save(city4);
		
		Domain domain1 = new Domain("Information Tech");
		Domain domain2 = new Domain("Finance");
		Domain domain3 = new Domain("Industry");
		domainService.save(domain1);
		domainService.save(domain2);
		domainService.save(domain3);
		
		
		
		Seeker seeker = new Seeker("seeker@gmail.com", passwordEncoder.encode("seeker"), "first", "last");
		seeker.addRole(seekerRole);
		seeker.setFirstName("First");
		seeker.setLastName("Last");
		seeker.setPhone("0624151985");
		seeker.setAddress("This is an address");
		seeker.setBirthDate("06/12/2012");
		seeker.setCity(cityService.findByName("Agadir"));
		Profile profile = new Profile();
		profile.setCv("\\\\cv\\\\cv-1");
		profile.setPhoto("\\\\profilePhotos\\\\photo-1");
		profile.setSpeciality("Légumes du Front-end");
		profile.setDescription("This is a description");
		profile.setPortefolio("portefolio.com");
		profile.setGithub("github.com/firstlast");
		seeker.setProfile(profile);
		
		
		
		
		Company company = new Company("company@gmail.com", passwordEncoder.encode("company"));
		company.addRole(companyRole);
		company.setName("Company Name");
		company.setPublicEmail("public.company@gmail.com");
		company.setDocumentPath("\\\\document\\\\document-2");
		company.setCity(cityService.findByName("Agadir"));
		company.setDomain(domainService.findByName("Industry"));
		CompanyProfile companyProfile = new CompanyProfile();
		companyProfile.setLogo("\\\\logos\\\\logo-2");
		companyProfile.setCoverPhoto("\\\\coverPhotos\\\\cover-2");
		companyProfile.setWebSite("company.com");
		company.setCompanyProfile(companyProfile);
		
		
		
		Admin admin = new Admin("admin@gmail.com", passwordEncoder.encode("admin"));
		admin.addRole(adminRole);
		
		userRepository.save(seeker);
		userRepository.save(company);
		userRepository.save(admin);
		
		
		
		internshipTypeRepository.save(new InternshipType("PFA"));
		internshipTypeRepository.save(new InternshipType("PFE"));
		internshipTypeRepository.save(new InternshipType("Paid"));
		internshipTypeRepository.save(new InternshipType("Pre-employment"));
		
		
		jobTypeRepository.save(new JobType("Part-Time"));
		jobTypeRepository.save(new JobType("CDI"));
		jobTypeRepository.save(new JobType("CDD"));
		
		
		durationRepository.save(new Duration("1 Month"));
		durationRepository.save(new Duration("2 Months"));
		durationRepository.save(new Duration("3 Months"));
		durationRepository.save(new Duration("4 Months"));
		durationRepository.save(new Duration("5 Months"));
		durationRepository.save(new Duration("6 Months"));
		
		
		
		Company company1 = new Company("company1@gmail.com", passwordEncoder.encode("company1"));
		company1.addRole(companyRole);
		company1.setName("Company 1");
		company1.setPhone("0652968335");
		company1.setPublicEmail("contact@company1.com");
		company1.setVerified(true);
		company1.setCity(cityService.findByName("Casablanca"));
		company1.setDomain(domainService.findByName("Information Tech"));
		userRepository.save(company1);
		
		JobOffer offer1 = new JobOffer();
		offer1.setDate("20/05/2021");
		offer1.setDescription("This is a description 1");
		offer1.setOpen(true);
		offer1.setTitle("Offer 1");
		offer1.setVerified(true);
		offer1.setCity(cityService.findByName("Agadir"));
		offer1.setDomain(domainService.findByName("Information Tech"));
		offer1.setCompany((Company) userRepository.findUserByEmail("company1@gmail.com"));
		offer1.setJobType(jobTypeRepository.findJobTypeByName("CDI"));
		
		InternshipOffer offer2 = new InternshipOffer();
		offer2.setDate("20/05/2021");
		offer2.setDescription("This is a description 2");
		offer2.setOpen(true);
		offer2.setTitle("Offer 2");
		offer2.setVerified(true);
		offer2.setCity(cityService.findByName("Casablanca"));
		offer2.setDomain(domainService.findByName("Finance"));
		offer2.setCompany((Company) userRepository.findUserByEmail("company1@gmail.com"));
		offer2.setInternshipType(internshipTypeRepository.findInternshipTypeByName("PFA"));
		offer2.setDuration(durationRepository.findByDuration("1 Month"));

		InternshipOffer offer3 = new InternshipOffer();
		offer3.setDate("20/05/2021");
		offer3.setDescription("This is a description 3");
		offer3.setOpen(true);
		offer3.setTitle("Offer 3");
		offer3.setVerified(true);
		offer3.setCity(cityService.findByName("Casablanca"));
		offer3.setDomain(domainService.findByName("Information Tech"));
		offer3.setCompany((Company) userRepository.findUserByEmail("company1@gmail.com"));
		offer3.setInternshipType(internshipTypeRepository.findInternshipTypeByName("Pré-embauche"));
		offer3.setDuration(durationRepository.findByDuration("3 Months"));


		
		offerRepository.save(offer1);
		offerRepository.save(offer2);
		offerRepository.save(offer3);
		
		
	}

}
