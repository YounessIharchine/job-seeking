package com.pfa.jobseeking.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfa.jobseeking.model.user.Company;
import com.pfa.jobseeking.repository.UserRepository;
import com.pfa.jobseeking.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	UserRepository userRepository;
	
	@Value("${storage.images.basePath}")
	String path;
	
	
	@PreAuthorize("hasRole('ROLE_COMPANY')")
	@Transactional
	@Override
	public void updateInfo(Map<String, String> map) throws IOException {
		String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		Company company = (Company)userRepository.findUserByEmail(authenticatedUserEmail);
		
		if(map.containsKey("publicEmail"))
			company.setPublicEmail(map.get("publicEmail"));
		if(map.containsKey("phone"))
			company.setPhone(map.get("phone"));
		if(map.containsKey("webSite"))
			company.getCompanyProfile().setWebSite(map.get("webSite"));
		if(map.containsKey("logo")) {
			String logoPath = "\\logos\\logo-" + company.getId() + ".png";
			byte[] imageBytes = Base64.getDecoder().decode(map.get("logo"));
			FileUtils.writeByteArrayToFile(new File(path+logoPath), imageBytes);
			company.getCompanyProfile().setLogo(logoPath.replace("\\", "\\\\"));
		}
		if(map.containsKey("coverPhoto")) {
			String coverPath = "\\coverPhotos\\cover-" + company.getId() + ".png";
			byte[] imageBytes = Base64.getDecoder().decode(map.get("coverPhoto"));
			FileUtils.writeByteArrayToFile(new File(path+coverPath), imageBytes);
			company.getCompanyProfile().setCoverPhoto(coverPath.replace("\\", "\\\\"));
		}
		

	}

}
