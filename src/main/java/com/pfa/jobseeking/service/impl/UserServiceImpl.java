package com.pfa.jobseeking.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.RoleRepository;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.rest.dto.UserDto;
import com.pfa.jobseeking.rest.exception.AlreadyExistsException;
import com.pfa.jobseeking.rest.exception.NotFoundException;
import com.pfa.jobseeking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
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


	@Transactional
	@Override
	public void save(UserDto userDto) throws AlreadyExistsException {
		
		if(userRepository.findUserByEmail(userDto.getEmail()) != null)
			throw new AlreadyExistsException("There is already an account with that email.");

		User user = null;
		
		if(userDto.getRole().equals("ROLE_SEEKER"))
			user = new Seeker();
		else if(userDto.getRole().equals("ROLE_COMPANY"))
			user = new Company();
		
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		Set<Role> role = new HashSet<>();
		role.add(roleRepository.findRoleByName(userDto.getRole()));
		user.setRoles(role);

		
		userRepository.save(user);
		
	}



	@Transactional
	@Override
	public void update(Map<String, String> map, int id) throws NotFoundException {
		Seeker seeker = (Seeker) userRepository.findById(id);
		
		if(seeker == null)
			throw new NotFoundException("There is no seeker with that id");
		else {
			if(map.containsKey("firstName"))
				seeker.setFirstName(map.get("firstName"));
			if(map.containsKey("lastName"))
				seeker.setLastName(map.get("lastName"));
			if(map.containsKey("phone"))
				seeker.setPhone(map.get("phone"));
			if(map.containsKey("address"))
				seeker.setAddress(map.get("address"));
			if(map.containsKey("birthDate"))
				seeker.setBirthDate(map.get("birthDate"));
		}
		
		userRepository.save(seeker);
	}

}







//@Override
//public List<User> findAll(Pageable pageable) {
//	return userRepository.findAll(pageable).getContent();
//}