package com.cs3733kakistocrat.group.model;

public class RemoteSite {
	String url;
	String api_key;

	public RemoteSite(String url,String api_key) {
		this.url = url;
		this.api_key = api_key;
	} 
	public String getUrl() {
		return this.url; 
	}
	public String getAPIKey() {
		return this.api_key; 
	}

}
