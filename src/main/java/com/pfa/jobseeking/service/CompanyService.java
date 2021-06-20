package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.Map;

import com.pfa.jobseeking.rest.dto.CompanyMandatoryInfoDto;

public interface CompanyService {

	void updateInfo(Map<String, String> map) throws IOException;

	void setMandatoryCompanyInfo(CompanyMandatoryInfoDto companyMandatoryInfoDto) throws IOException;

}
