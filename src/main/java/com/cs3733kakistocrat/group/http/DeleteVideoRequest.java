package com.cs3733kakistocrat.group.http;

public class DeleteVideoRequest {
	String name;
	String videoID;
	
	public String getName( ) { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	public DeleteVideoRequest() {
	}
	
	public DeleteVideoRequest (String name, String videoID) {
		this.name = name;
		this.videoID=  videoID;
	}
	
	public String toString() {
		return "DeleteVideo(" + name + ")";
	}
}
