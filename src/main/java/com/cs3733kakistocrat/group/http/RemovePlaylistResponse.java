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
	
	public boolean equals(RemovePlaylistResponse resp) {
		return (this.error.equals(resp.error)&&this.statusCode==resp.statusCode&&this.playlistName.equals(resp.playlistName));
	}
	
	public String toString() {
		if (statusCode == 200) {  
			return "DeleteResponse(" + playlistName+ ")";
		} else {
			return "ErrorResult(" + playlistName + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
