package com.cs3733kakistocrat.group;

import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.SearchVideosRequest;
import com.cs3733kakistocrat.group.http.SearchVideosResponse;
import com.cs3733kakistocrat.group.model.Video;

public class SearchVideosHandler implements RequestHandler<SearchVideosRequest,SearchVideosResponse>{
	
	public LambdaLogger logger;
	
	List<Video> findVideos(String charSearch, String sentSearch) throws Exception {
		logger.log("in searchVideos");
		VideosDAO dao = new VideosDAO();
		
		return dao.searchVideos(charSearch, sentSearch);
	}
	
	@Override
	public SearchVideosResponse handleRequest(SearchVideosRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to search videos");

		SearchVideosResponse response = null;
		logger.log(req.toString());
		
		try {
			List<Video> list = findVideos(req.getcharSearch(), req.getsentSearch());
			response = new SearchVideosResponse(req.getcharSearch(), req.getsentSearch(), list, 200);
		} catch (Exception e) {
			response = new SearchVideosResponse(req.getcharSearch(), req.getcharSearch(),403, e.getMessage());
		}

		return response;
	}
}
