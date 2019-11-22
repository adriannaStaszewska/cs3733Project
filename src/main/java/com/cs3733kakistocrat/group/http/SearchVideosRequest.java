package com.cs3733kakistocrat.group.http;

public class SearchVideosRequest {
	String search;
	
	public String getSearch( ) { return search; }
	public void setSearch(String search) { this.search = search; }
	
	public SearchVideosRequest() {
	}
	
	public SearchVideosRequest (String search) {
		this.search = search;
	}
	
	public String toString() {
		return "DeleteVideo(" + search + ")";
	}
}
