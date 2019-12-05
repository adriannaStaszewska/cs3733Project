package group;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.SearchVideosHandler;
import com.cs3733kakistocrat.group.http.SearchVideosRequest;
import com.cs3733kakistocrat.group.http.SearchVideosResponse;
import com.google.gson.Gson;

public class SearchVideosHandlerTest extends LambdaTest{
	
	@Test 
	public void serchTest() {
		SearchVideosRequest req = new SearchVideosRequest("random", "", true, true);
		req.toString();
		String input = new Gson().toJson(req); 
		SearchVideosHandler handler = new SearchVideosHandler();
		SearchVideosRequest req2 =  new Gson().fromJson(input, SearchVideosRequest.class);
		SearchVideosResponse resp = handler.handleRequest(req, createContext("search videos"));
	    resp.toString();
		Assert.assertEquals(200, resp.statusCode);
	}

}
