package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.http.CreatePlaylistRequest;
import com.cs3733kakistocrat.group.http.CreatePlaylistResponse;
import com.cs3733kakistocrat.group.model.Playlist;



public class CreatePlaylistHandler implements RequestHandler<CreatePlaylistRequest,CreatePlaylistResponse>{

	LambdaLogger logger;
	
	@Override
	public CreatePlaylistResponse handleRequest(CreatePlaylistRequest input, Context context) {
		
		CreatePlaylistResponse response;
		try {
			if (createPlaylist(input.getName())) {
				response = new CreatePlaylistResponse(input.getName());
			} else {
				response = new CreatePlaylistResponse(input.getName(), 422);
			}
		}catch (Exception e) {
			response = new CreatePlaylistResponse("Unable to create playlist: " + input.getName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean createPlaylist(String name) throws Exception {
		if (logger != null) { logger.log("in createPlaylist"); }
		PlaylistsDAO dao = new PlaylistsDAO();
		
		// check if present
		//Playlist exist = dao.getPlaylist(name);
		Playlist playlist = new Playlist(name);
		//if (exist == null) {
			return dao.addPlaylist(playlist);
		/*} else {
			return false;
		}*/
	}
	
	

}
