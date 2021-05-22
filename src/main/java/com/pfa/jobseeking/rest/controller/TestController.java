package com.pfa.jobseeking.rest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.City;
import com.pfa.jobseeking.model.Domain;
import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.CityService;
import com.pfa.jobseeking.service.DomainService;
import com.pfa.jobseeking.service.InternshipTypeService;
import com.pfa.jobseeking.service.JobTypeService;
import com.pfa.jobseeking.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	DomainService domainService;
	
	@Autowired
	InternshipTypeService internshipTypeService;
	
	@Autowired
	JobTypeService jobTypeService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@GetMapping("/test")
	String test() {
		
		return "test";
		
	}
	
	
	
	@GetMapping("/authenticated")
	User showAuthenticatedUser(Principal principal) throws NotFoundException {
		
		User user = userService.findUserByEmail(principal.getName());
		return user;
		
	}
	
	
	
	@GetMapping("/fill")
	String fillDatabase() throws AlreadyExistsException {
		
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
		seeker.setCity(cityService.findByName("Agadir"));
		Company company = new Company("company@gmail.com", passwordEncoder.encode("company"));
		company.addRole(companyRole);
		company.setCity(cityService.findByName("Agadir"));
		company.setDomain(domainService.findByName("Industry"));
		Admin admin = new Admin("admin@gmail.com", passwordEncoder.encode("admin"));
		admin.addRole(adminRole);
		
		userService.save(seeker);
		userService.save(company);
		userService.save(admin);
		
		
		
		Company company1 = new Company("company1@gmail.com", passwordEncoder.encode("company1"));
		company1.addRole(companyRole);
		company1.setName("Company 1");
		company1.setPhone("0652968335");
		company1.setPublicEmail("contact@company1.com");
		company1.setVerified(true);
		company1.setCity(cityService.findByName("Casablanca"));
		company1.setDomain(domainService.findByName("Information Tech"));
		
		JobOffer offer1 = new JobOffer();
		offer1.setDate("20/05/2021");
		offer1.setDescription("This is a description 1");
		offer1.setOpen(true);
		offer1.setTitle("Offer 1");
		offer1.setVerified(true);
		offer1.setCity(cityService.findByName("Agadir"));
		offer1.setDomain(domainService.findByName("Information Tech"));
		InternshipOffer offer2 = new InternshipOffer();
		offer2.setDate("20/05/2021");
		offer2.setDescription("This is a description 2");
		offer2.setOpen(true);
		offer2.setTitle("Offer 2");
		offer2.setVerified(true);
		offer2.setCity(cityService.findByName("Casablanca"));
		offer2.setDomain(domainService.findByName("Finance"));
		InternshipOffer offer3 = new InternshipOffer();
		offer3.setDate("20/05/2021");
		offer3.setDescription("This is a description 3");
		offer3.setOpen(true);
		offer3.setTitle("Offer 3");
		offer3.setVerified(true);
		offer3.setCity(cityService.findByName("Casablanca"));
		offer3.setDomain(domainService.findByName("Information Tech"));
		
		company1.addOffer(offer1);
		company1.addOffer(offer2);
		company1.addOffer(offer3);
		
		userService.save(company1);
		
		
		internshipTypeService.save("PFA");
		internshipTypeService.save("PFE");
		internshipTypeService.save("Rénuméré");
		internshipTypeService.save("Pré-embauche");
		
		
		jobTypeService.save("Part-Time");
		jobTypeService.save("CDI");
		jobTypeService.save("CDD");


		
		
		return "Database Filled";
	}
	
}
