package com.cs3733kakistocrat.group.http;

public class AllVideosInPlaylistRequest {
	String playlistName;
	
	public String getPlaylistName( ) { return playlistName; }
	public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }
	
	public AllVideosInPlaylistRequest() {
	}
	
	public AllVideosInPlaylistRequest (String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistName + ")";
	}

}
