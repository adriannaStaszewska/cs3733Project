package com.cs3733kakistocrat.group.http;

import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Video;

public class SearchVideosResponse {
	public final String charSearch;
	public final String sentSearch;
	public final List<Video> list;
	public final int statusCode;
	public final String error;
	
	public SearchVideosResponse (String charSearch, String sentSearch, List<Video> list, int statusCode) {
		this.charSearch = charSearch;
		this.sentSearch = sentSearch;
		this.list = list;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public SearchVideosResponse (String charSearch, String sentSearch, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.list = new ArrayList<Video>();
		this.error = errorMessage;
		this.charSearch = charSearch;
		this.sentSearch = sentSearch;
	} 
	public boolean equals(SearchVideosResponse resp) {
		return (this.charSearch.equals(resp.charSearch)&&this.sentSearch.equals(resp.sentSearch)
				&&this.statusCode==resp.statusCode&&this.error.equals(resp.error));
	}
	
	public String toString() {
		if (list == null) { return "Search: " + charSearch + " " + sentSearch + " - NoVideos"; }
		return "Search: " + charSearch + " " + sentSearch + " - FoundVideos(" + list.size() + ")";
	}
}
