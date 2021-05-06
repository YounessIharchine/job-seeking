package com.pfa.jobseeking.model.offer;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ApplicationKey implements Serializable{

	private int seekerId;
	private int offerId;
	
	
	public ApplicationKey() { }


	public int getSeekerId() {
		return seekerId;
	}
	public int getOfferId() {
		return offerId;
	}


	public void setSeekerId(int seekerId) {
		this.seekerId = seekerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + offerId;
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
		ApplicationKey other = (ApplicationKey) obj;
		if (offerId != other.offerId)
			return false;
		if (seekerId != other.seekerId)
			return false;
		return true;
	}
	
	
	
}
