package com.cs3733kakistocrat.group.http;

public class SearchVideosRequest {
	String charSearch;
	String sentSearch;
	
	public String getcharSearch( ) { return charSearch; }
	public void setcharSearch(String charSearch) { this.charSearch = charSearch; }
	
	public String getsentSearch( ) { return sentSearch; }
	public void setsentSearch(String sentSearch) { this.sentSearch = sentSearch; }
	
	public SearchVideosRequest() {
	}
	
	public SearchVideosRequest (String charSearch, String sentSearch, boolean local, boolean remote) {
		this.charSearch = charSearch;
		this.sentSearch = sentSearch;
	}
	 
	public String toString() {
		return "DeleteVideo(" + charSearch + " " + sentSearch + ")";
	}
}
