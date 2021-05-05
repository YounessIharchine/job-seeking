package com.pfa.jobseeking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private CustomAuthenticationProvider authenticationProvider;
	
	@Autowired
	private CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    
    
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.httpBasic()
		.authenticationEntryPoint(basicAuthenticationEntryPoint)
		.and()
		.authorizeRequests()
			.antMatchers("/test").permitAll()
			.antMatchers("/users").hasRole("ADMIN")
			.anyRequest().authenticated();

	}

}