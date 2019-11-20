package com.cs3733kakistocrat.group.http;

public class DeleteVideoRequest {
	String name;
	String video_id;
	
	public String getName( ) { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public DeleteVideoRequest() {
	}
	
	public DeleteVideoRequest (String name, String video_id) {
		this.name = name;
	}
	
	public String toString() {
		return "DeleteVideo(" + name + ")";
	}
}
