package com.pfa.jobseeking.service;

import java.io.IOException;

public interface ApplicationService {

	byte[] getCv(int seekerId, int offerId) throws IOException;

	byte[] getCoverLetter(int seekerId, int offerId) throws IOException;

}
