package com.cs3733kakistocrat.group.http;

public class CreatePlaylistRequest {
	String name;
	
	public String getName( ) { return name; }
	public void setName(String name) { this.name = name; }
	
	public CreatePlaylistRequest() {
	}
	
	public CreatePlaylistRequest (String name) {
		this.name = name;
	}
	
	public String toString() {
		return "CreatePlaylist(" + name + ")";
	}

}
