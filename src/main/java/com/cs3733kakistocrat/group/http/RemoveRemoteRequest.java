package com.cs3733kakistocrat.group.http;

public class RemoveRemoteRequest {
	String url;
	
	public RemoveRemoteRequest() {
	}
	
	public RemoveRemoteRequest (String url, String api_key) {
		this.url = url;
	}
	
	public void setUrl (String url) {
		this.url= url;
	}
	
	
	public String getUrl() {
		return url;
	}
	public String toString() {
		return "DeleteRemote(" + url + ")";
	}
}

