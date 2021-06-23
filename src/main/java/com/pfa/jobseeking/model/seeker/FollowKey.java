package com.pfa.jobseeking.model.seeker;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class FollowKey implements Serializable {

	private int seekerId;
	private int companyId;
	
	
	
	public FollowKey() { }

	
	
	public int getSeekerId() {
		return seekerId;
	}
	public int getCompanyId() {
		return companyId;
	}

	
	
	public void setSeekerId(int seekerId) {
		this.seekerId = seekerId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + seekerId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowKey other = (FollowKey) obj;
		if (companyId != other.companyId)
			return false;
		if (seekerId != other.seekerId)
			return false;
		return true;
	}
	
	
	
}
