package com.pfa.jobseeking.rest.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfa.jobseeking.model.offer.InternshipOffer;
import com.pfa.jobseeking.model.offer.JobOffer;
import com.pfa.jobseeking.model.user.Admin;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleRepository roleRepository;
	
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
	String fillDatabase() {
		
		Role seekerRole = new Role("ROLE_SEEKER");
		Role companyRole = new Role("ROLE_COMPANY");
		Role adminRole = new Role("ROLE_ADMIN");
		roleRepository.save(seekerRole);
		roleRepository.save(companyRole);
		roleRepository.save(adminRole);
		
		Seeker seeker = new Seeker("seeker@gmail.com", "seeker", "first", "last");
		seeker.addRole(seekerRole);
		Company company = new Company("company@gmail.com", "company");
		company.addRole(companyRole);
		Admin admin = new Admin("admin@gmail.com", "admin");
		admin.addRole(adminRole);
		
		userService.save(seeker);
		userService.save(company);
		userService.save(admin);
		
		Company company1 = new Company("company1@gmail.com", "company1");
		company1.addRole(companyRole);
		company1.setName("Company 1");
		company1.setPhone("0652968335");
		company1.setPublicEmail("contact@company1.com");
		company1.setVerified(true);
		JobOffer offer1 = new JobOffer();
		offer1.setDate("20/05/2021");
		offer1.setDescription("This is a description 1");
		offer1.setOpen(true);
		offer1.setTitle("Offer 1");
		offer1.setVerified(true);
		company1.addOffer(offer1);
		InternshipOffer offer2 = new InternshipOffer();
		offer2.setDate("20/05/2021");
		offer2.setDescription("This is a description 2");
		offer2.setOpen(true);
		offer2.setTitle("Offer 2");
		offer2.setVerified(true);
		company1.addOffer(offer2);
		InternshipOffer offer3 = new InternshipOffer();
		offer3.setDate("20/05/2021");
		offer3.setDescription("This is a description 3");
		offer3.setOpen(true);
		offer3.setTitle("Offer 3");
		offer3.setVerified(true);
		company1.addOffer(offer3);
		
		
		userService.save(company1);
		
		return "Database Filled";
	}
	
}
