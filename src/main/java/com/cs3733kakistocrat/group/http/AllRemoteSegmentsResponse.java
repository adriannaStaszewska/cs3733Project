package com.cs3733kakistocrat.group.http;

import java.util.ArrayList;
import java.util.List;

import com.cs3733kakistocrat.group.model.Segment;

public class AllRemoteSegmentsResponse {
	public final List<Segment> segments;
	public final int statusCode;
	public final String error;
	
	public AllRemoteSegmentsResponse (List<Segment> segments, int code) {
		this.segments = segments;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllRemoteSegmentsResponse (int code, String errorMessage) {
		this.segments = new ArrayList<Segment>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (segments == null) { return "NoVideos"; }
		return "AllVideos(" + segments.size() + ")";
	}

}
