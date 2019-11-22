package com.cs3733kakistocrat.group;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.http.AllVideosInPlaylistRequest;
import com.cs3733kakistocrat.group.http.AllVideosResponse;

import com.cs3733kakistocrat.group.model.Video;

public class ListAllVideosInPlaylistHandler implements RequestHandler<AllVideosInPlaylistRequest, AllVideosResponse> {
	public LambdaLogger logger;
	
	List<Video> getAllVideos(String playlist_name) throws Exception {
		VideoToPlaylistDAO dao = new VideoToPlaylistDAO();	
		return dao.getAllVideos(playlist_name);
	}
	
	@Override
	public AllVideosResponse handleRequest(AllVideosInPlaylistRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all playlists");

		AllVideosResponse response;
		try {
			String playlistName =  input.getPlaylistName();
			List<Video> list = getAllVideos(playlistName);
			
			response = new AllVideosResponse(list, 200);
		} catch (Exception e) {
			response = new AllVideosResponse(403, e.getMessage());
		}
		
		return response;
	}

}
