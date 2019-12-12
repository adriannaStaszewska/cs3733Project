package com.cs3733kakistocrat.group.http;

public class DeleteVideoResponse {
	public final String name;
	public final int statusCode;
	public final String error;
	
	public DeleteVideoResponse (String name, int statusCode) {
		this.name = name;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	// 200 means success
	public DeleteVideoResponse (String name, int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
		this.name = name;
	}
	public boolean equals(DeleteVideoResponse resp) {
		return (this.name.equals(resp.name)&&this.statusCode==resp.statusCode&&resp.error.equals(resp.error));
	}
	public String toString() {
		if (statusCode == 200) {  
			return "DeleteResponse(" + name + ")";
		} else {
			return "ErrorResult(" + name + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
