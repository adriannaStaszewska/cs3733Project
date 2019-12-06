package com.cs3733kakistocrat.group;

import java.io.ByteArrayInputStream;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
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
		System.out.println(input.getVideoName() + input.getCharacter() + input.getSentence());
		try {
			System.out.println(input.getVideoFile());
			byte[] encoded = java.util.Base64.getDecoder().decode(input.getVideoFile());
			Video video = new Video(input.getVideoName(), input.getCharacter(), input.getSentence());
			if (uploadVideo(input.getVideoName(), encoded, video)) {
				response = new UploadVideoResponse(input.getVideoName());
			} else {
				response = new UploadVideoResponse(input.getVideoName(), 422);
			}
		}catch (Exception e) {
			response = new UploadVideoResponse("Unable to upload video: " + input.getVideoName()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean uploadVideo(String name, byte[]  contents, Video video) throws Exception {
		if (logger != null) { logger.log("in uploadVideo"); }
		VideosDAO dao = new VideosDAO();
		
		String bucket = "3733kakistocrat";
		if(System.getenv("TESTING") != null) {
			bucket = "3733kakistocrattests";
		}
		
		if (this.s3 == null) {
			System.out.println("Create s3");
//			logger.log("attach to S3 request");
			this.s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			System.out.println("created s3");
//			logger.log("attach to S3 succeed");
		}
 
		ByteArrayInputStream bais = new ByteArrayInputStream(contents);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(contents.length);
		//in S3 bucket
		try {
			
			PutObjectResult res = this.s3.putObject(new PutObjectRequest(bucket, "videos"+name + ".ogg", bais, omd)
			    .withCannedAcl( CannedAccessControlList.PublicRead));
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!!!: " + e.getMessage());
			return false;
		}

		System.out.println(name);
		String url = "https://3733kakistocrat.s3.us-east-2.amazonaws.com/videos/" + name + ".ogg";
		if(System.getenv("TESTING") != null) {
			url = "https://3733kakistocrattests.s3.us-east-2.amazonaws.com/videos/" + name + ".ogg";
		}
		video.setUrl(url);
		dao.addVideo(video);
		
		return true;
	}
	

}
