package com.cs3733kakistocrat.group.http;

public class CreateRemoteResponse {
	public final String response;
	public final int httpCode;
	
	public CreateRemoteResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public CreateRemoteResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}

}
