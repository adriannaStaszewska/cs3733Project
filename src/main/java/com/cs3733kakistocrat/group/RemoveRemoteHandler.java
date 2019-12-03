package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.RemotesDAO;
import com.cs3733kakistocrat.group.http.RemovePlaylistRequest;
import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;
import com.cs3733kakistocrat.group.http.RemoveRemoteRequest;
import com.cs3733kakistocrat.group.http.RemoveRemoteResponse;
import com.cs3733kakistocrat.group.model.RemoteSite;

public class RemoveRemoteHandler implements RequestHandler<RemoveRemoteRequest,RemoveRemoteResponse>{
	public LambdaLogger logger;
	
	@Override
	public RemoveRemoteResponse handleRequest(RemoveRemoteRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		RemoveRemoteResponse response = null;
		logger.log(req.toString());

		RemotesDAO dao = new RemotesDAO();

		
		RemoteSite remote = new RemoteSite(req.getUrl());
		try {
			if (dao.removeRemote(remote)) {
				response = new RemoveRemoteResponse(req.getUrl(), 200);
			} else {
				response = new RemoveRemoteResponse(req.getUrl(), 422, "Unable to delete remote site.");
			}
		} catch (Exception e) {
			response = new RemoveRemoteResponse(req.getUrl(), 403, "Unable to delete remote site: " + req.getUrl() + "(" + e.getMessage() + ")");
		}

		return response;
	}

}