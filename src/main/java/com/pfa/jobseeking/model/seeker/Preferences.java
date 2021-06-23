package com.pfa.jobseeking.model.seeker;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.pfa.jobseeking.model.offer.InternshipType;
import com.pfa.jobseeking.model.offer.JobType;
import com.pfa.jobseeking.model.user.Seeker;

@Entity
public class Preferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@OneToOne(mappedBy = "preferences")
	Seeker seeker;
	
	
	boolean canRelocate;
	
	@ManyToMany
	@JoinTable(name = "preference_internship", joinColumns = @JoinColumn(name = "preference_id"), inverseJoinColumns = @JoinColumn(name = "internship_type_id"))
	Set<InternshipType> internshipTypes;
	@ManyToMany
	@JoinTable(name = "preference_job", joinColumns = @JoinColumn(name = "preference_id"), inverseJoinColumns = @JoinColumn(name = "job_type_id"))
	Set<JobType> jobTypes;
	
	
	
	public Preferences() { }

	
	
	public int getId() {
		return id;
	}
	public Seeker getSeeker() {
		return seeker;
	}
	public boolean isCanRelocate() {
		return canRelocate;
	}
	public Set<InternshipType> getInternshipTypes() {
		return internshipTypes;
	}
	public Set<JobType> getJobTypes() {
		return jobTypes;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setSeeker(Seeker seeker) {
		this.seeker = seeker;
	}
	public void setCanRelocate(boolean canRelocate) {
		this.canRelocate = canRelocate;
	}
	public void setInternshipTypes(Set<InternshipType> internshipTypes) {
		this.internshipTypes = internshipTypes;
	}
	public void setJobTypes(Set<JobType> jobTypes) {
		this.jobTypes = jobTypes;
	}
	
	
	
}
