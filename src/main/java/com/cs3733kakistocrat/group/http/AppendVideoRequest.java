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
	
	public AppendVideoRequest(String videoID, String playlistName, boolean remote) {
		this.videoID = videoID;
		this.playlistName = playlistName;
		this.remote = remote;
	}
	
	public AppendVideoRequest(String playlistName, boolean remote, String character, String url, String text) {
		//this.videoID = videoID;
		this.playlistName = playlistName;
		this.remote = remote;
		this.character = character;
		this.url = url;
		this.text = text;
	}
	
	public boolean equals(AppendVideoRequest req) {
		return (this.playlistName.equals(req.playlistName)&&this.remote==req.remote
				&&this.character.equals(req.character)&&this.url.equals(req.url)&&this.text.contentEquals(req.text));
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
	
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setRemote(boolean remote) {
		this.remote = remote;
	}
	
	public void setCharacter(String character) {
		this.character = character;
	}

	public String toString() {
		return "AppendVideo(" + videoID + playlistName + ")";
	}


}
