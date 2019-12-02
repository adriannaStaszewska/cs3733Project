package com.cs3733kakistocrat.group.http;

public class SearchVideosRequest {
	String search;
	boolean character;
	boolean sentence;
	
	public String getSearch( ) { return search; }
	public void setSearch(String search) { this.search = search; }
	
	public boolean getCharacter( ) { return character; }
	public void setCharacter(boolean character) { this.character = character; }
	
	public boolean getSentence( ) { return sentence; }
	public void setSentence(boolean sentence) { this.sentence = sentence; }
	
	public SearchVideosRequest() {
	}
	
	public SearchVideosRequest (String search, boolean character, boolean sentence) {
		this.search = search;
		this.character = character;
		this.sentence = sentence;
	}
	
	public String toString() {
		return "DeleteVideo(" + search + " " + character + " " + sentence + ")";
	}
}
