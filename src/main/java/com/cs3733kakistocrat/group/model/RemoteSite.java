package com.cs3733kakistocrat.group.model;

public class RemoteSite {
	String url;
	String api_key;

	public RemoteSite(String url,String api_key) {
		this.url = url;
		this.api_key = api_key;
	} 
	public RemoteSite(String url) {
		this.url = url;
	} 
	public String getUrl() {
		return this.url; 
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

}
