package com.cs3733kakistocrat.group.http;

public class UpdateRemoteStatusRequest {
	String videoID;
	Boolean status;
	
	public UpdateRemoteStatusRequest(String videoID, Boolean status) {
		this.videoID = videoID;
		this.status = status;
	}
	
	public UpdateRemoteStatusRequest() {
		
	}
	
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	

}
