package com.pfa.jobseeking.service;

import java.io.IOException;
import java.util.Map;

public interface CompanyService {

	void updateInfo(Map<String, String> map) throws IOException;

}
