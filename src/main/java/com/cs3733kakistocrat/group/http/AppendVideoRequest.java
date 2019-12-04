package com.cs3733kakistocrat.group.http;

public class AppendVideoRequest {
	String videoID;
	String playlistName;
	
	public AppendVideoRequest(String videoID, String playlistName) {
		this.videoID = videoID;
		this.playlistName = playlistName;
	}
	
	public AppendVideoRequest() {
		
	}
	
	public String getPlaylistName( ) { 
		return playlistName; 
	
	}
	public void setPlaylistName(String playlistName) { 
		this.playlistName = playlistName; 
	}
	
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String video_id) {
		this.videoID = video_id;
	}
	
	public String toString() {
		return "AppendVideo(" + videoID + playlistName + ")";
	}


}
