package com.cs3733kakistocrat.group;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.AllRemoteSegmentsResponse;
import com.cs3733kakistocrat.group.model.Segment;


public class ListAllRemoteSegmentsHandler implements RequestHandler<Object,AllRemoteSegmentsResponse>{

public LambdaLogger logger;
	
	List<Segment> getAllRemoteSegments() throws Exception {
		logger.log("in getAllRemoteVideos");
		VideosDAO dao = new VideosDAO();
		
		return dao.getAllRemoteSegments();
	}
	
	
	@Override
	public AllRemoteSegmentsResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		AllRemoteSegmentsResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<Segment> list = getAllRemoteSegments();
			
			response = new AllRemoteSegmentsResponse(list, 200);
		} catch (Exception e) {
			response = new AllRemoteSegmentsResponse(403, e.getMessage());
		}
		
		return response;
	}
}
