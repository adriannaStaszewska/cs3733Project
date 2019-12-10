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
	
	public AppendVideoRequest(String videoID, String playlistName, boolean remote, String character, String url, String text) {
		this.videoID = videoID;
		this.playlistName = playlistName;
		this.remote = remote;
		this.character = character;
		this.url = url;
		this.text = text;
	}
	
	public AppendVideoRequest(String playlistName, boolean remote, String character, String url, String text) {
		//this.videoID = videoID;
		this.playlistName = playlistName;
		this.remote = remote;
		this.character = character;
		this.url = url;
		this.text = text;
	}
	
	
	
	public boolean isRemote() {
		return remote;
	}

	public void setRemote(boolean remote) {
		this.remote = remote;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
