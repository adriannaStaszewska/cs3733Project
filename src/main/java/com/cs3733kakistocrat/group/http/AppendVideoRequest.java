package com.cs3733kakistocrat.group.http;

public class AppendVideoRequest {
	String videoID;
	String playlistName;
	boolean remote;
	String character;
	String url;
	String text;
	
	public AppendVideoRequest(String videoID, String playlistName) {
		this.videoID = videoID;
		this.playlistName = playlistName;
	}
	

	
	public AppendVideoRequest(String playlistName, boolean remote, String character, String url, String text) {
		//this.videoID = videoID;
		this.playlistName = playlistName;
		this.remote = remote;
		this.character = character;
		this.url = url;
		this.text = text;
	}
	
	public AppendVideoRequest() {
		
	}
	
	public boolean isRemote() {
		return remote;
	}

	public String getCharacter() {
		return character;
	}
	public String getUrl() {
		return url;
	}


	public String getText() {
		return text;
	}
	public String getPlaylistName( ) { 
		return playlistName; 
	
	}
	
	public String getVideoID() {
		return videoID;
	}

	public String toString() {
		return "AppendVideo(" + videoID + playlistName + ")";
	}


}
