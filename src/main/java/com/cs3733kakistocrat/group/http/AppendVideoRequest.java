package com.cs3733kakistocrat.group.http;

public class AppendVideoRequest {
	String video_id;
	String playlistName;
	
	public AppendVideoRequest(String video_id, String playlistName) {
		this.video_id = video_id;
		this.playlistName = playlistName;
	}
	public String getPlaylistName( ) { 
		return playlistName; 
	
	}
	public void setPlaylistName(String playlistName) { 
		this.playlistName = playlistName; 
	}
	
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	
	public String toString() {
		return "AppendVideo(" + video_id + playlistName + ")";
	}


}
