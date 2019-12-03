package com.cs3733kakistocrat.group.http;

public class RemoveRemoteResponse {
	public final String url;
	public final String api_key;
	public final int statusCode;
	public final String error;
	
	public RemoveRemoteResponse (String url, String api_key, int statusCode) {
		this.url = url;
		this.api_key = api_key;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public RemoveRemoteResponse (String url, String api_key, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.url = url;
		this.api_key = api_key;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "DeleteResponse(" + url+ ")";
		} else {
			return "ErrorResult(" + url + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}