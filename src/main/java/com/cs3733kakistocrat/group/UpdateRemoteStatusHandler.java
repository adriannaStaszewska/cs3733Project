package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.CreateRemoteResponse;
import com.cs3733kakistocrat.group.http.Response;
import com.cs3733kakistocrat.group.http.UpdateRemoteStatusRequest;
import com.cs3733kakistocrat.group.model.Video;


public class UpdateRemoteStatusHandler implements RequestHandler<UpdateRemoteStatusRequest,Response>{

	LambdaLogger logger;
	@Override
	public Response handleRequest(UpdateRemoteStatusRequest input, Context context) {
		Response response = null;
		VideosDAO dao = new VideosDAO();
		try {
			if (dao.updateRemote(input.getVideoID(), input.getStatus())) {
				response = new Response(input.getVideoID());
			} else {
				response = new Response(input.getVideoID(), 422);
			}
		}catch (Exception e) {
			response = new Response("Unable to edit status: " + input.getVideoID()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
		
	}

}
