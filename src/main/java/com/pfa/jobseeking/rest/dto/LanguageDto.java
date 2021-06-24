package com.pfa.jobseeking.rest.dto;

public class LanguageDto {

	String name;
	String level;
	
	
	public LanguageDto() { }

	
	public LanguageDto(String name, String level) {
		this.name = name;
		this.level = level;
	}
	
	
	public String getName() {
		return name;
	}
	public String getLevel() {
		return level;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	

}
