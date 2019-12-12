package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.http.RemoveVideoRequest;
import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;

public class RemoveVideoFromPlaylistHandler implements RequestHandler<RemoveVideoRequest,DeleteVideoResponse>{

	LambdaLogger logger;
	@Override
	public DeleteVideoResponse handleRequest(RemoveVideoRequest input, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete video");

		DeleteVideoResponse response = null;
		logger.log(input.toString());

		VideoToPlaylistDAO dao = new VideoToPlaylistDAO();
		
		System.out.println(input.getVideoID());
		System.out.println(input.getPosition());
		System.out.println(input.getPlaylistName());
		
		Video video = new Video(input.getVideoID());
		int position = input.getPosition();
		Playlist pl = new Playlist(input.getPlaylistName());
		
		try {
			if (dao.removeVideo(video, pl, position)) {
				response = new DeleteVideoResponse(input.getVideoID(), 200);
			} else {
				response = new DeleteVideoResponse(input.getVideoID(), 422, "Unable to remove video.");
			}
		} catch (Exception e) {
			response = new DeleteVideoResponse(input.getVideoID(), 403, "Unable to remove video " + input.getVideoID() + "(" + e.getMessage() + ")");
		}

		return response;
	}
}

