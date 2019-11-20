package com.cs3733kakistocrat.group.model;

import java.util.UUID;

public class Video {

	String name;
	String url;
	String character;
	String sentence;
	String video_id;
	boolean remotely_accessible;
	
	
	public Video(String name, String url, String character, String sentence) {
		this.name = name;
		this.url = url;
		this.character = character;
		this.sentence = sentence;
		this.remotely_accessible = false;
		this.video_id = UUID.randomUUID().toString();
	}
	
	public Video(String name, String video_id) {
		this.name = name;
		this.url = "";
		this.character = "";
		this.sentence = "";
		this.remotely_accessible = false;
		this.video_id = video_id;
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

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public boolean isRemotely_accessible() {
		return remotely_accessible;
	}

	public void setRemotely_accessible(boolean remotely_accessible) {
		this.remotely_accessible = remotely_accessible;
	}
	
	
}
