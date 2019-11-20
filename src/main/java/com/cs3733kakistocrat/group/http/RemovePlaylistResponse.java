package com.cs3733kakistocrat.group.http;

public class RemovePlaylistResponse {
	public final String playlistName;
	public final int statusCode;
	public final String error;
	
	public RemovePlaylistResponse (String name, int statusCode) {
		this.playlistName = name;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public RemovePlaylistResponse (String name, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.playlistName = name;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "DeleteResponse(" + playlistName+ ")";
		} else {
			return "ErrorResult(" + playlistName + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
