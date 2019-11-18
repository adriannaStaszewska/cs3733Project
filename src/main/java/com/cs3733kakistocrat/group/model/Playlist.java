package com.cs3733kakistocrat.group.model;

import java.util.ArrayList;

public class Playlist {

	String name;
	ArrayList<Video> videos;
	
	public Playlist(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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
