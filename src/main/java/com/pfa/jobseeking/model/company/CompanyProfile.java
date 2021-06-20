package com.pfa.jobseeking.model.company;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.user.Company;

@Entity
public class CompanyProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToOne(mappedBy = "companyProfile")
	Company company;
	
	String logo;
	String coverPhoto;
	String webSite;
	
	@OneToMany(mappedBy = "companyProfile", cascade = CascadeType.ALL)
	Set<Photo> photos;
	
	@OneToMany(mappedBy = "companyProfile", cascade = CascadeType.ALL)
	Set<Paragraph> paragraphs;
	
	
	
	public CompanyProfile() { }



	public int getId() {
		return id;
	}
	public Company getCompany() {
		return company;
	}
	public String getLogo() {
		return logo;
	}
	public String getCoverPhoto() {
		return coverPhoto;
	}
	public String getWebSite() {
		return webSite;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public Set<Paragraph> getParagraphs() {
		return paragraphs;
	}



	public void setId(int id) {
		this.id = id;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setCoverPhoto(String coverPhoto) {
		this.coverPhoto = coverPhoto;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	public void setParagraphs(Set<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	
	
	
	public void addParagraph(Paragraph paragraph) {
		this.paragraphs.add(paragraph);
	}
	public void addPhoto(Photo photo) {
		this.photos.add(photo);
	}
}
