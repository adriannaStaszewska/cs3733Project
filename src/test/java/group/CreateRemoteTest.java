package group;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreateRemoteHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
import com.cs3733kakistocrat.group.http.CreateRemoteRequest;
import com.cs3733kakistocrat.group.http.CreateRemoteResponse;
import com.cs3733kakistocrat.group.http.RemoveRemoteRequest;
import com.cs3733kakistocrat.group.http.RemoveRemoteResponse;
import com.google.gson.Gson;

public class CreateRemoteTest extends LambdaTest{
	
	@Test
	public void createPlaylist() {
		CreateRemoteRequest req = new CreateRemoteRequest("TEST Url", "Some API key");
		String input = new Gson().toJson(req); 
		CreateRemoteHandler handler = new CreateRemoteHandler();
    	CreateRemoteRequest request = new Gson().fromJson(input, CreateRemoteRequest.class);
       
        CreateRemoteResponse resp = handler.handleRequest(req, createContext("create remote url"));
        Assert.assertEquals(200, resp.httpCode);
        
        RemoveRemoteRequest delReq = new RemoveRemoteRequest("TEST Url", "Some API key");
        RemoveRemoteResponse delRes = new RemoveRemoteHandler().handleRequest(delReq, createContext("delete remote url"));
	}
	

}
