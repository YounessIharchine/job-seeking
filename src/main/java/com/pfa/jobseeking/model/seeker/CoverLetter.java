package com.pfa.jobseeking.model.seeker;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pfa.jobseeking.model.user.Seeker;

@Entity
@Table(name = "cover_letter")
public class CoverLetter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String contenu;
	String signaturePath;
	
	@OneToOne(mappedBy = "coverLetter")
	Seeker seeker;
	
	
	
	public CoverLetter() { }

	

	public int getId() {
		return id;
	}
	public String getContenu() {
		return contenu;
	}
	public String getSignaturePath() {
		return signaturePath;
	}
	public Seeker getSeeker() {
		return seeker;
	}


	public void setId(int id) {
		this.id = id;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}

	
	
	
	
}
