package com.cs3733kakistocrat.group;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.http.AllPlaylistsResponse;
import com.cs3733kakistocrat.group.model.Playlist;


public class ListAllPlaylistsHandler implements RequestHandler<Object,AllPlaylistsResponse> {
	
	public LambdaLogger logger;
	
	List<Playlist> getAllPlaylists() throws Exception {
		logger.log("in getAllPlaylists");
		PlaylistsDAO dao = new PlaylistsDAO();	
		return dao.getAllPlaylists();
	}
	
	@Override
	public AllPlaylistsResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all playlists");

		AllPlaylistsResponse response;
		try {
			List<Playlist> list = getAllPlaylists();
			
			response = new AllPlaylistsResponse(list, 200);
		} catch (Exception e) {
			response = new AllPlaylistsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
