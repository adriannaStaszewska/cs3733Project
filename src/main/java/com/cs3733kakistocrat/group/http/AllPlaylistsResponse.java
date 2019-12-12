package com.cs3733kakistocrat.group.http;

import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Playlist;

public class AllPlaylistsResponse {
	public final List<Playlist> list;
	public final int statusCode;
	public final String error;
		
	public AllPlaylistsResponse (List<Playlist> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
		
	public AllPlaylistsResponse (int code, String errorMessage) {
		this.list = new ArrayList<Playlist>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public boolean equals(AllPlaylistsResponse resp) {
		return (this.statusCode==resp.statusCode&&this.error.equals(resp.error)&&this.list.equals(resp.list));
	}
		
	public String toString() {
		if (list == null) { return "NoPlaylists"; }
		return "AllPlaylists(" + list.size() + ")";
	}
}

