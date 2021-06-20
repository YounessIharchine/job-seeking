package com.pfa.jobseeking.rest.dto;

public class TextDto {

	String text;
	
	public TextDto() { }

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TextDto(String text) {
		this.text = text;
	}
}
