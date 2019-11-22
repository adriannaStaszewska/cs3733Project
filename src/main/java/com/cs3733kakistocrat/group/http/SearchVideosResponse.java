package com.cs3733kakistocrat.group.http;

import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Video;

public class SearchVideosResponse {
	public final String search;
	public final List<Video> list;
	public final int statusCode;
	public final String error;
	
	public SearchVideosResponse (String search, List<Video> list, int statusCode) {
		this.search = search;
		this.list = list;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public SearchVideosResponse (String search, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.list = new ArrayList<Video>();
		this.error = errorMessage;
		this.search = search;
	}
	
	public String toString() {
		if (list == null) { return "Search: " + search + " - NoVideos"; }
		return "Search: " + search + " - FoundVideos(" + list.size() + ")";
	}
}
