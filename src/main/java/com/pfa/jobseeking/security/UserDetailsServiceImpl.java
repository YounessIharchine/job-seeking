package com.pfa.jobseeking.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email);
		
		if(user == null)
			throw new UsernameNotFoundException("There is no user with this email : " + email);
		
		
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getRoles())
        	authorities.add(new SimpleGrantedAuthority(role.getName()));
			
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword() , authorities);
	}

}
