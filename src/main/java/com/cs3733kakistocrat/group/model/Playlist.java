package com.cs3733kakistocrat.group.model;

import java.util.ArrayList;

public class Playlist {

	String playlistName;
	ArrayList<Video> videos;
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}


	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}


	public boolean addVideo(Video v) {
		try {
		videos.add(v);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean removeVideo(Video v) {
		try {
			videos.remove(v);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
