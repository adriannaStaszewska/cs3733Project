package com.cs3733kakistocrat.group.http;

public class CreateRemoteResponse {
	public final String response;
	public final String api;
	public final int httpCode;
	
	public CreateRemoteResponse (String s, int code) {
		this.response = s;
		this.api = "";
		this.httpCode = code;
	}
	
	public CreateRemoteResponse (String s, String a, int code) {
		this.response = s;
		this.api = a;
		this.httpCode = code;
	}
	
	// 200 means success
	public CreateRemoteResponse (String s, String a) {
		this.response = s;
		this.api = a;
		this.httpCode = 200;
	}
	
	
	public String toString() {
		return "Response(" + response + ", " + api + ")";
	}

}
