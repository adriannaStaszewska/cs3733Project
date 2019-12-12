package com.cs3733kakistocrat.group.http;

public class UploadVideoResponse {
	public final String response;
	public final int httpCode;
	
	public UploadVideoResponse (String s, int code) {
		this.response = s;
		this.httpCode = code;
	}
	
	public UploadVideoResponse (String s) {
		this.response = s;
		this.httpCode = 200;
	}
	public boolean equals(UploadVideoResponse resp) {
		return (this.response.equals(resp.response)&&this.httpCode==resp.httpCode);
	}
	public String toString() {
		return "Response(" + response + ")";
	}
}
