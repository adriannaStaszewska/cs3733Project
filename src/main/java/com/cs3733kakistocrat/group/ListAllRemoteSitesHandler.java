package com.cs3733kakistocrat.group;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.RemotesDAO;
import com.cs3733kakistocrat.group.http.AllRemotesResponse;
import com.cs3733kakistocrat.group.model.RemoteSite;

public class ListAllRemoteSitesHandler  implements RequestHandler<Object,AllRemotesResponse> {
	public LambdaLogger logger;
	
	List<RemoteSite> getAllRemotes() throws Exception {
		logger.log("in getAllRemotes");
		RemotesDAO dao = new RemotesDAO();
		
		return dao.getAllRemotes();
	}
	
	
	@Override
	public AllRemotesResponse handleRequest(Object input, Context context)  {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all constants");

		AllRemotesResponse response;
		try {
			// get all user defined constants AND system-defined constants.
			// Note that user defined constants override system-defined constants.
			List<RemoteSite> list = getAllRemotes();
			
			response = new AllRemotesResponse(list, 200);
		} catch (Exception e) {
			response = new AllRemotesResponse(403, e.getMessage());
		}
		
		return response;
	}

}


