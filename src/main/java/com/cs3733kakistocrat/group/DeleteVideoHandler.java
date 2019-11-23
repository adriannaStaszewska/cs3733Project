package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.DeleteVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.model.Video;


public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest,DeleteVideoResponse>{
	
	public LambdaLogger logger;
	
	@Override
	public DeleteVideoResponse handleRequest(DeleteVideoRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete video");

		DeleteVideoResponse response = null;
		logger.log(req.toString());

		VideosDAO dao = new VideosDAO();
		Video video = new Video(req.getName(), req.getVideoID());
		
		try {
			if (dao.deleteVideo(video)) {
				response = new DeleteVideoResponse(req.getName(), 200);
			} else {
				response = new DeleteVideoResponse(req.getName(), 422, "Unable to delete video.");
			}
		} catch (Exception e) {
			response = new DeleteVideoResponse(req.getName(), 403, "Unable to delete video " + req.getName() + "(" + e.getMessage() + ")");
		}

		return response;
	}

}
