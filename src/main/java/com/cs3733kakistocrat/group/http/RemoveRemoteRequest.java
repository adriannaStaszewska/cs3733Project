package com.cs3733kakistocrat.group.http;

public class RemoveRemoteRequest {
	String url;
	
	public String getUrl() { return url; }
	public void setPlaylistName(String playlistName) { this.url = url; }
	
	public RemoveRemoteRequest() {
	}
	
	public RemoveRemoteRequest (String url) {
		this.url= url;
	}
	
	public String toString() {
		return "DeleteRemote(" + url + ")";
	}
}

