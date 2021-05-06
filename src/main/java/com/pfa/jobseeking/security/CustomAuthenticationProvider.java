package com.pfa.jobseeking.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.pfa.jobseeking.model.user.Role;
import com.pfa.jobseeking.model.user.User;
import com.pfa.jobseeking.service.UserService;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByEmail(email);
        
        if(user != null) {
        	
        	if(user.getPassword().equals(password)) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                for(Role role : user.getRoles())
                	authorities.add(new SimpleGrantedAuthority(role.getName()));

                return new UsernamePasswordAuthenticationToken(email, password, authorities);
        	}
        	else {
        		throw new BadCredentialsException("Wrong Password");
        	}

        }
        else {
        	throw new BadCredentialsException("Wrong Username");
        }

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
