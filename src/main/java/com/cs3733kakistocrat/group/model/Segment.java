package com.cs3733kakistocrat.group.model;

public class Segment {

	String url;
	String text;
	String character;
	
	public Segment(String url, String text, String character) {
		this.url = url;
		this.text = text;
		this.character = character;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	
	
}
