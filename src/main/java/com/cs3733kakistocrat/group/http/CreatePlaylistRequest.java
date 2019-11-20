package com.cs3733kakistocrat.group.http;

public class CreatePlaylistRequest {
	String playlistName;
	
	public String getPlaylistName( ) { return playlistName; }
	public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }
	
	public CreatePlaylistRequest() {
	}
	
	public CreatePlaylistRequest (String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistName + ")";
	}

}
