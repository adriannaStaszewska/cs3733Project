package com.cs3733kakistocrat.group.http;

public class UploadVideoRequest {
	
	String videoName;
	String videoFile;
	String character;
	String sentence;
	Boolean remote;

	public UploadVideoRequest() {
	
	}

	public UploadVideoRequest(String videoName, String videoFile, String character, String sentence, Boolean remote) {
		this.videoName = videoName;
		this.videoFile = videoFile;
		this.character = character;
		this.sentence = sentence;
		this.remote = remote;
	}

	public UploadVideoRequest(String videoName, String videoFile, String character, String sentence) {
		this.videoName = videoName;
		this.videoFile = videoFile;
		this.character = character;
		this.sentence = sentence;
		this.remote = false;
	}
	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
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

	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	public String toString() {
		return "UploadVideo(" + videoName + ")";
	}
}
