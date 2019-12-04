package com.cs3733kakistocrat.group.model;

import java.util.UUID;

public class Video {

	String name;
	String url;
	String character;
	String sentence;
	String videoID;
	boolean remotely_accessible;
	
	public Video(String videoID, String name, String url, String character, String sentence) {
		this.name = name;
		this.url = url;
		this.character = character;
		this.sentence = sentence;
		this.remotely_accessible = false;
		this.videoID = videoID;
	}
	
	public Video(String videoID, String name, String url, String character, String sentence, Boolean remotely_accessible) {
		this.name = name;
		this.url = url;
		this.character = character;
		this.sentence = sentence;
		this.remotely_accessible = remotely_accessible;
		this.videoID = videoID;
	}
	
	public Video(String name, String url, String character, String sentence) {
		this.name = name;
		this.url = url;
		this.character = character;
		this.sentence = sentence;
		this.remotely_accessible = false;
		this.videoID = UUID.randomUUID().toString();
	}
	
	public Video(String name, String videoID) {
		this.name = name;
		this.url = "";
		this.character = "";
		this.sentence = "";
		this.remotely_accessible = false;
		this.videoID = videoID;
	}
	
	public Video(String videoID) {
		this.name = "";
		this.url = "";
		this.character = "";
		this.sentence = "";
		this.remotely_accessible = false;
		this.videoID = videoID;
	}
	
	public Video(String name, String character, String sentence) {
		this.name = name;
		this.url = "";
		this.character = character;
		this.sentence = sentence;
		this.remotely_accessible = false;
		this.videoID = UUID.randomUUID().toString();
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

	public String getVideoID() {
		return videoID;
	}

	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}

	public boolean isRemotely_accessible() {
		return remotely_accessible;
	}

	public void setRemotely_accessible(boolean remotely_accessible) {
		this.remotely_accessible = remotely_accessible;
	}
	
	
}
