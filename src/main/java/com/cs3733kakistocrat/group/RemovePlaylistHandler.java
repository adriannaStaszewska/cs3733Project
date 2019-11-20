package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.http.AllVideosResponse;
import com.cs3733kakistocrat.group.http.RemovePlaylistRequest;
import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;
import com.cs3733kakistocrat.group.model.Playlist;


public class RemovePlaylistHandler implements RequestHandler<RemovePlaylistRequest,RemovePlaylistResponse>{
	
	public LambdaLogger logger;
	
	@Override
	public RemovePlaylistResponse handleRequest(RemovePlaylistRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		RemovePlaylistResponse response = null;
		logger.log(req.toString());

		PlaylistsDAO dao = new PlaylistsDAO();

		
		Playlist playlist = new Playlist(req.getPlaylistName());
		try {
			if (dao.removePlaylist(playlist)) {
				response = new RemovePlaylistResponse(req.getPlaylistName(), 200);
			} else {
				response = new RemovePlaylistResponse(req.getPlaylistName(), 422, "Unable to delete playlist.");
			}
		} catch (Exception e) {
			response = new RemovePlaylistResponse(req.getPlaylistName(), 403, "Unable to delete playlist: " + req.getPlaylistName() + "(" + e.getMessage() + ")");
		}

		return response;
	}

}
