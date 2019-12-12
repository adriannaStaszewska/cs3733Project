package com.cs3733kakistocrat.group.http;

import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.RemoteSite;


public class AllRemotesResponse {
	public final List<RemoteSite> list;
	public final int statusCode;
	public final String error;
	
	public AllRemotesResponse (List<RemoteSite> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllRemotesResponse (int code, String errorMessage) {
		this.list = new ArrayList<RemoteSite>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public boolean equals(AllRemotesResponse resp) {
		return (this.statusCode==resp.statusCode&&this.error.equals(resp.error));
	}
	
	public String toString() {
		if (list == null) { return "NoRemotes"; }
		return "AllRemotes(" + list.size() + ")";
	}
}
