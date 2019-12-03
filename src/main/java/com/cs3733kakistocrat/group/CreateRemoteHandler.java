package com.cs3733kakistocrat.group;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.RemotesDAO;
import com.cs3733kakistocrat.group.http.CreateRemoteRequest;
import com.cs3733kakistocrat.group.http.CreateRemoteResponse;
import com.cs3733kakistocrat.group.model.RemoteSite;

public class CreateRemoteHandler implements RequestHandler<CreateRemoteRequest,CreateRemoteResponse>{
	LambdaLogger logger;
	
	@Override
	public CreateRemoteResponse handleRequest(CreateRemoteRequest input, Context context) {
		
		CreateRemoteResponse response;
		
		try {
			System.out.println(input.getUrl());
			if (createRemoteSite(input.getUrl(), input.getApi_key())) {
				response = new CreateRemoteResponse(input.getUrl());
			} else {
				response = new CreateRemoteResponse(input.getUrl(), 400);
			}
		}catch (Exception e) {
			response = new CreateRemoteResponse("Unable to create playlist: " + input.getUrl()+ " (" + e.getMessage() + ")", 400);
		}
		return response;
	}
	
	boolean createRemoteSite(String url, String api_key) throws Exception {
		if (logger != null) { logger.log("in createPlaylist"); }
		RemotesDAO dao = new RemotesDAO();
		
		// check if present
		RemoteSite exist = dao.getRemote(url);
		RemoteSite site = new RemoteSite(url, api_key);
		if (exist == null) {
			return dao.addRemote(site);
		} else {
			return false;
		}
	}
	
	

}
