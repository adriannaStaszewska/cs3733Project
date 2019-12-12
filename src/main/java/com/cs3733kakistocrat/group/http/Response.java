package com.cs3733kakistocrat.group.http;

public class Response {
	public final String response;
	public final int httpCode;
	
	public Response (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	// 200 means success
	public Response (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	public boolean equals(Response resp) {
		return (this.response.equals(resp.response)&&this.httpCode==resp.httpCode);
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
