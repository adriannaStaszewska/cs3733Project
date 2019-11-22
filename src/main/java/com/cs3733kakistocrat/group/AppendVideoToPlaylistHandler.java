package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.AppendVideoRequest;
import com.cs3733kakistocrat.group.http.Response;
import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;

public class AppendVideoToPlaylistHandler implements RequestHandler<AppendVideoRequest,Response>{
	LambdaLogger logger;
	
	@Override
	public Response handleRequest(AppendVideoRequest input, Context context) {
		
		Response response;
		VideosDAO vidDAO = new VideosDAO();
		PlaylistsDAO plDAO = new PlaylistsDAO();
		try {
			Video video = vidDAO.getVideo(input.getVideo_id());
			Playlist playlist = plDAO.getPlaylist(input.getPlaylistName());
			if (appendVideoToPlaylist(video, playlist)) {
				response = new Response(input.getPlaylistName());
			} else {
				response = new Response(input.getPlaylistName(), 400);
			}
		}catch (Exception e) {
			response = new Response("Unable add video"+ input.getVideo_id() + " to playlist: " + input.getPlaylistName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean appendVideoToPlaylist(Video vid, Playlist pl) throws Exception {
		if (logger != null) { logger.log("in appendVideoToPlaylist"); }
		VideoToPlaylistDAO dao = new VideoToPlaylistDAO();
		if(dao.appendVideoToPlaylist(vid, pl)) {
			return true;
		} 
		return false;
		
	}
	
	

}
