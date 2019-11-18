package com.cs3733kakistocrat.group;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.AllVideosResponse;
import com.cs3733kakistocrat.group.model.Video;


public class ListAllVideosHandler implements RequestHandler<Object,AllVideosResponse> {

	public LambdaLogger logger;
	
	List<Video> getAllVideos() throws Exception {
		logger.log("in getConstants");
		VideosDAO dao = new VideosDAO();
		
		return dao.getAllVideos();
	}
	
	
	@Override
	public AllVideosResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		AllVideosResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Video> list = getAllVideos();
			
			response = new AllVideosResponse(list, 200);
		} catch (Exception e) {
			response = new AllVideosResponse(403, e.getMessage());
		}
		
		return response;
	}

}
