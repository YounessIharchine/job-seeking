package com.pfa.jobseeking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl);
	}
	

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManagerBean());
		JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter();

		
		http
		.csrf().disable()
		.cors()
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			//.antMatchers("/api/users").authenticated()
			.antMatchers("/test", "/fill", "/api/**", "/register").permitAll()
			.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(jwtAuthenticationFilter)
		.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		

		//cors is activated for everything, security problem
		//problem occured in : delete a city
		
	}
	
	
	
	
//	@Autowired
//    private CustomAuthenticationProvider authenticationProvider;
//	
//	@Autowired
//	private CustomBasicAuthenticationEntryPoint basicAuthenticationEntryPoint;
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
//    }
//    
//    
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http
//		.httpBasic()
//		.authenticationEntryPoint(basicAuthenticationEntryPoint)
//		.and()
//		.csrf().disable()
//		.authorizeRequests()
//			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//			//.antMatchers("/api/users").authenticated()
//			.antMatchers("/test", "/fill", "/api/**").permitAll()
//			.anyRequest().authenticated();
//
//	}
	
	
	

}
