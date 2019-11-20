package com.cs3733kakistocrat.group;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.UploadVideoRequest;
import com.cs3733kakistocrat.group.http.UploadVideoResponse;
import com.cs3733kakistocrat.group.model.Video;

public class UploadVideoHandler implements RequestHandler<UploadVideoRequest,UploadVideoResponse>{

	LambdaLogger logger;
	private AmazonS3 s3 = null;
	
	@Override
	public UploadVideoResponse handleRequest(UploadVideoRequest input, Context context) {
		
		UploadVideoResponse response;
		
		try {
			byte[] encoded = java.util.Base64.getDecoder().decode(input.getBase64EncodedFile());
			Video video = new Video(input.getName(), input.getCharacter(), input.getSentence());
			if (uploadVideo(input.getName(), encoded, video)) {
				response = new UploadVideoResponse(input.getName());
			} else {
				response = new UploadVideoResponse(input.getName(), 422);
			}
		}catch (Exception e) {
			response = new UploadVideoResponse("Unable to upload video: " + input.getName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean uploadVideo(String name, byte[]  contents, Video video) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		VideosDAO dao = new VideosDAO();
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}
		
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		
		//in S3 bucket
		PutObjectResult res = s3.putObject(new PutObjectRequest("3733kakistocrat", "videos/" + name, bais, omd));
		
		//not have to actually upload to database 
		String url = "3733kakistocrat/videos/" + name + bais + omd;
		video.setUrl(url);
		dao.addVideo(video);
		
		return true;
	}
	

}
