package com.cs3733kakistocrat.group.http;

public class RemovePlaylistRequest {
String playlistName;
	
	public String getPlaylistName( ) { return playlistName; }
	public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }
	
	public RemovePlaylistRequest() {
	}
	
	public RemovePlaylistRequest (String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String toString() {
		return "CreatePlaylist(" + playlistName + ")";
	}
}
