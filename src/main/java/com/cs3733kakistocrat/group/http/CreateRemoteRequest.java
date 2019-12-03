package com.cs3733kakistocrat.group.http;

public class CreateRemoteRequest {
	String url;
	String api_key;
	
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public String getUrl( ) { return url; }

	
	public CreateRemoteRequest() {
	}
	
	public CreateRemoteRequest (String url, String api_key) {
		this.url = url;
		this.api_key = api_key;
	}
	
	public void setUrl (String url) {
		this.url = url;
	}
	
	public String toString() {
		return "CreatePlaylist(" + url + ")";
	}

}
