package com.pfa.jobseeking.security;

import static com.pfa.jobseeking.security.SecurityConstants.EXPIRATION_TIME;
import static com.pfa.jobseeking.security.SecurityConstants.HEADER_STRING;
import static com.pfa.jobseeking.security.SecurityConstants.SECRET;
import static com.pfa.jobseeking.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.model.user.Seeker;
import com.pfa.jobseeking.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private AuthenticationManager authenticationManager;

	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
		setFilterProcessesUrl("/authenticate");
	}	

	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException 
	{
		JwtTokenRequest user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), JwtTokenRequest.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
	}
	
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		UserRepository userRepository = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext()).getBean(UserRepository.class);
		User springUser = (User) authResult.getPrincipal();
		String jwtToken = Jwts.builder().setSubject(springUser.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority authority : springUser.getAuthorities())
			roles.add(authority.getAuthority());
		  
		final Map<String, Object> body = new HashMap<>();
		body.put("status", HttpServletResponse.SC_OK);
		body.put("message", "Authentication Successful");
		body.put("roles", roles);
		if(roles.contains("ROLE_SEEKER")) {
			Seeker seeker = (Seeker)userRepository.findUserByEmail(springUser.getUsername());
			body.put("id", seeker.getId());
			body.put("name", seeker.getFirstName() + " " + seeker.getLastName());
		}
		else if(roles.contains("ROLE_COMPANY")) {
			Company company = (Company)userRepository.findUserByEmail(springUser.getUsername());
			body.put("id", company.getId());
			body.put("name", company.getName());
		}

		//fuck you rah makantch ghatle3
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}


	@Override
	protected void unsuccessfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		  
		final Map<String, Object> body = new HashMap<>();
		body.put("status", HttpServletResponse.SC_FORBIDDEN);
		body.put("message", failed.getMessage());
		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
	
	
	
}
