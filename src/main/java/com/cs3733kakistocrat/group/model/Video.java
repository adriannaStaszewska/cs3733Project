package com.cs3733kakistocrat.group.model;

public class Video {

	String name;
	String url;
	String character;
	String sentence;
	boolean remotelyAccessible;
	
	public Video(String name, String url, String character, String sentence) {
		this.name = name;
		this.url = url;
		this.character = character;
		this.sentence = sentence;
		this.remotelyAccessible = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getSentence() {
		return sentence;
	}
	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
	public boolean isRemotelyAccessible() {
		return remotelyAccessible;
	}
	public void setRemotelyAccessible(boolean remotelyAccessible) {
		this.remotelyAccessible = remotelyAccessible;
	}
	
}
