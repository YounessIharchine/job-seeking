package com.pfa.jobseeking.rest.dto;

import java.util.List;

public class ApplicationWithoutCvDto {

	String coverLetter;
	List<Integer> experiencesIds;
	List<Integer> educationsIds;
	List<Integer> projectsIds;
	List<Integer> skillsIds;
	List<Integer> languagesIds;
	
	
	
	public ApplicationWithoutCvDto() { }

	

	public ApplicationWithoutCvDto(String coverLetter, List<Integer> experiencesIds, List<Integer> educationsIds,
			List<Integer> projectsIds, List<Integer> skillsIds, List<Integer> languagesIds) {
		this.coverLetter = coverLetter;
		this.experiencesIds = experiencesIds;
		this.educationsIds = educationsIds;
		this.projectsIds = projectsIds;
		this.skillsIds = skillsIds;
		this.languagesIds = languagesIds;
	}

	

	public String getCoverLetter() {
		return coverLetter;
	}
	public List<Integer> getExperiencesIds() {
		return experiencesIds;
	}
	public List<Integer> getEducationsIds() {
		return educationsIds;
	}
	public List<Integer> getProjectsIds() {
		return projectsIds;
	}
	public List<Integer> getSkillsIds() {
		return skillsIds;
	}
	public List<Integer> getLanguagesIds() {
		return languagesIds;
	}

	

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}
	public void setExperiencesIds(List<Integer> experiencesIds) {
		this.experiencesIds = experiencesIds;
	}
	public void setEducationsIds(List<Integer> educationsIds) {
		this.educationsIds = educationsIds;
	}
	public void setProjectsIds(List<Integer> projectsIds) {
		this.projectsIds = projectsIds;
	}
	public void setSkillsIds(List<Integer> skillsIds) {
		this.skillsIds = skillsIds;
	}
	public void setLanguagesIds(List<Integer> languagesIds) {
		this.languagesIds = languagesIds;
	}


	public void addExperience(int experienceId) {
		this.experiencesIds.add(experienceId);
	}
	public void removeExperience(int experienceId) {
		this.experiencesIds.remove(experienceId);
	}
	
	public void addEducation(int educationId) {
		this.educationsIds.add(educationId);
	}
	public void removeEducation(int educationId) {
		this.educationsIds.remove(educationId);
	}
	
	public void addProject(int projectId) {
		this.projectsIds.add(projectId);
	}
	public void removeProject(int projectId) {
		this.projectsIds.remove(projectId);
	}
	
	public void addSkill(int skillId) {
		this.skillsIds.add(skillId);
	}
	public void removeSkill(int skillId) {
		this.skillsIds.remove(skillId);
	}
	
	public void addLanguage(int languageId) {
		this.languagesIds.add(languageId);
	}
	public void removeLanguage(int languageId) {
		this.languagesIds.remove(languageId);
	}
	
}
