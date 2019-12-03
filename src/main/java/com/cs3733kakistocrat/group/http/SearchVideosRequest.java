package com.cs3733kakistocrat.group.http;

public class SearchVideosRequest {
	String charSearch;
	String sentSearch;
	
	public String getCharacterSearch( ) { return charSearch; }
	public void setCharacterSearch(String charSearch) { this.charSearch = charSearch; }
	
	public String getSentenceSearch( ) { return sentSearch; }
	public void setSentenceSearch(String sentSearch) { this.sentSearch = sentSearch; }
	
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
