package com.cs3733kakistocrat.group;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.DeleteVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.model.Video;


public class DeleteVideoHandler implements RequestHandler<DeleteVideoRequest,DeleteVideoResponse>{
	
	public LambdaLogger logger;
	private AmazonS3 s3 = null;
	
	@Override
	public DeleteVideoResponse handleRequest(DeleteVideoRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete video");

		DeleteVideoResponse response = null;
		logger.log(req.toString());

		VideosDAO dao = new VideosDAO();
		VideoToPlaylistDAO playlistDAO = new VideoToPlaylistDAO();
		Video video = new Video(req.getName(), req.getVideoID());
		
		try {
			
			if (!playlistDAO.removeVideoFromAllPlaylists(video.getVideoID())) {
				response = new DeleteVideoResponse(req.getName(), 422, "Unable to delete video.");
			}
			
			if (dao.deleteVideo(video) && deleteVideoFromTheBucket(req.getName())) {
				response = new DeleteVideoResponse(req.getName(), 200);
			} else {
				response = new DeleteVideoResponse(req.getName(), 422, "Unable to delete video.");
			}
		} catch (Exception e) {
			response = new DeleteVideoResponse(req.getName(), 403, "Unable to delete video " + req.getName() + "(" + e.getMessage() + ")");
		}

		return response;
	}
	
	boolean deleteVideoFromTheBucket(String name) {
		String bucket = "3733kakistocrat";
		
		if(System.getenv("TESTING") != null) {
			bucket = "3733kakistocrattests";
		}
		
		if (s3 == null) {
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
		}
		try {
			 s3.deleteObject(new DeleteObjectRequest(bucket, "videos/" + name + ".ogg"));
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		return true;
	}
}
