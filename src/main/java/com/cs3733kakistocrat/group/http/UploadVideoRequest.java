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
	public boolean equals(UploadVideoRequest req) {
		return (this.remote==req.remote&&this.videoFile.equals(req.videoFile)&&this.videoName.equals(req.videoName)
				&&this.character.equals(req.character)&&this.sentence.equals(req.sentence));
	}
	public String getVideoName() {
		return videoName;
	}

	public String getVideoFile() {
		return videoFile;
	}
	public String getCharacter() {
		return character;
	}

	public String getSentence() {
		return sentence;
	}

	public Boolean getRemote() {
		return remote;
	}

	public String toString() {
		return "UploadVideo(" + videoName + ")";
	}
}
