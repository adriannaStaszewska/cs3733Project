package com.cs3733kakistocrat.group.http;

public class CreateRemoteRequest {
	String url;
	
	public String getUrl( ) { return url; }
	public void setPlaylistName(String url) { this.url = url; }
	
	public CreateRemoteRequest() {
	}
	
	public CreateRemoteRequest (String remote) {
		this.url = remote;
	}
	
	public String toString() {
		return "CreatePlaylist(" + url + ")";
	}

}
