package com.cs3733kakistocrat.group.http;

public class RemoveVideoRequest {
	String videoID;
	String playlistName;
	int position;
	
	public RemoveVideoRequest(String videoID, String playlistName, int position) {
		this.videoID = videoID;
		this.playlistName = playlistName;
		this.position = position;
	}
	
	public RemoveVideoRequest() {

	}
	
	@Override
	public String toString() {
		return "RemoveVideoRequest [videoID=" + videoID + ", playlistName=" + playlistName + ", position=" + position
				+ "]";
	}
	public String getVideoID() {
		return videoID;
	}
	public void setVideoID(String videoID) {
		this.videoID = videoID;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	
}
