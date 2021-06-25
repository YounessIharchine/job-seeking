package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.CompanyCreationRequest;
import com.pfa.jobseeking.model.company.CompanyProfile;
import com.pfa.jobseeking.model.seeker.Profile;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.CityRepository;
import com.pfa.jobseeking.repository.CompanyCreationRequestRepository;
import com.pfa.jobseeking.repository.DomainRepository;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.CompanyDto;
import com.pfa.jobseeking.rest.dto.SeekerDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.rest.exception.UnauthorizedException;
import com.pfa.jobseeking.rest.response.UserResponse;
import com.pfa.jobseeking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	CityRepository cityRepository;
	
	@Autowired
	DomainRepository domainRepository;
	
	@Autowired
	CompanyCreationRequestRepository companyCreationRequestRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Value("${storage.images.basePath}")
	String path;
	
	@Transactional
	@Override
	public void saveSeeker(SeekerDto seekerDto) throws AlreadyExistsException {
		
		if(userRepository.findUserByEmail(seekerDto.getEmail()) != null)
			throw new AlreadyExistsException("There is already an account with that email.");
		
		Seeker seeker = new Seeker();
		seeker.setEmail(seekerDto.getEmail());
		seeker.setPassword(passwordEncoder.encode(seekerDto.getPassword()));
		Set<Role> role = new HashSet<>();
		role.add(roleRepository.findRoleByName("ROLE_SEEKER"));
		seeker.setRoles(role);
		
		Profile seekerProfile = new Profile();
		seeker.setProfile(seekerProfile);
		
		userRepository.save(seeker);
	}

	@Transactional
	@Override
	public void saveCompany(CompanyDto companyDto) throws AlreadyExistsException, IOException {
		
		if(userRepository.findUserByEmail(companyDto.getEmail()) != null)
			throw new AlreadyExistsException("There is already an account with that email.");
		
		Company company = new Company();
		company.setEmail(companyDto.getEmail());
		company.setPassword(passwordEncoder.encode(companyDto.getPassword()));
		Set<Role> role = new HashSet<>();
		role.add(roleRepository.findRoleByName("ROLE_COMPANY"));
		company.setRoles(role);
		company.setName(companyDto.getName());
		company.setPublicEmail(companyDto.getPublicEmail());
		company.setPhone(companyDto.getPhone());
		company.setCity(cityRepository.findCityByName(companyDto.getCity()));
		company.setDomain(domainRepository.findDomainByName(companyDto.getDomain()));
		
		company = (Company)userRepository.save(company);
		
		String documentPath = "\\documents\\document-" + company.getId() + ".pdf";
		byte[] documentBytes = Base64.getDecoder().decode(companyDto.getDocument());
		FileUtils.writeByteArrayToFile(new File(path+documentPath), documentBytes);
		company.setDocumentPath(documentPath.replace("\\", "\\\\"));
		
		CompanyProfile companyProfile = new CompanyProfile();
		company.setCompanyProfile(companyProfile);
		
		userRepository.save(company);
		
		CompanyCreationRequest request = new CompanyCreationRequest();
		request.setCompany((Company) userRepository.findUserByEmail(companyDto.getEmail()));
		request.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		companyCreationRequestRepository.save(request);
	}
	
	
	
	@Transactional
	@Override
	public UserResponse getAuthenticatedUserInfo() throws UnauthorizedException {
		
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

		if(authenticatedUserEmail.equals("anonymousUser"))
			throw new UnauthorizedException("You didn't include the token in the header");
		
		User user = userRepository.findUserByEmail(authenticatedUserEmail);
		
		if(user == null)
			throw new UnauthorizedException("Requested User doesn't exist");
		
		String role = null;
		for(Role iteratedRole : user.getRoles()) {
			if(iteratedRole.getName().equals("ROLE_SEEKER"));
				role = iteratedRole.getName();
			if(iteratedRole.getName().equals("ROLE_COMPANY"));
				role = iteratedRole.getName();
			if(iteratedRole.getName().equals("ROLE_ADMIN"));
				role = iteratedRole.getName();
		}
		
		UserResponse response = new UserResponse();
		response.setId(user.getId());
		response.setRole(role);
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	
	@Override
	public User findById(int id) throws NotFoundException {
		User user = userRepository.findById(id);
		
		if(user == null)
			throw new NotFoundException("The id does not correspond to any user");
		
		return user;
	}
	
	
	
	@Override
	public User findUserByEmail(String email) throws NotFoundException {
		User user = userRepository.findUserByEmail(email);
		
		if(user == null)
			throw new NotFoundException("There is no user with that email");
		
		return user;
	}

	
	@Transactional
	@Override
	public User save(User user) {
		return userRepository.save(user);
	}



}







//@Override
//public List<User> findAll(Pageable pageable) {
//	return userRepository.findAll(pageable).getContent();
//}