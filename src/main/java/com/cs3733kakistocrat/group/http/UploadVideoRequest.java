package com.cs3733kakistocrat.group.http;

public class UploadVideoRequest {
	String name;
	String base64EncodedFile;
	String character;
	String sentence;
	boolean remote;
	
	public UploadVideoRequest(String name, String base64EncodedFile, String character, String sentence, boolean remote) {
		this.name = name;
		this.base64EncodedFile = base64EncodedFile;
		this.character = character;
		this.sentence = sentence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBase64EncodedFile() {
		return base64EncodedFile;
	}

	public void setBase64EncodedFile(String base64EncodedFile) {
		this.base64EncodedFile = base64EncodedFile;
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


	public String toString() {
		return "UploadVideo(" + name + ")";
	}
	
	public void setRemote(boolean remote) {
		this.remote = remote;
	}
	
	public boolean getRemote() {
		return this.remote;
	}
}
