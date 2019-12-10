package group;

import java.io.IOException;

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
	   void testSuccessInput(String incomingJson) throws IOException {
	    	CreateRemoteHandler handler = new CreateRemoteHandler();
	    	CreateRemoteRequest req = new Gson().fromJson(incomingJson, CreateRemoteRequest.class);
	       
	        CreateRemoteResponse resp = handler.handleRequest(req, createContext("create"));
	        Assert.assertEquals(200, resp.httpCode);
	    }
	    
	    void testFailureInput(String incomingJson, int errorCode) throws IOException {
	    	CreateRemoteHandler handler = new CreateRemoteHandler();
	    	CreateRemoteRequest req = new Gson().fromJson(incomingJson, CreateRemoteRequest.class);
	       
	        CreateRemoteResponse resp = handler.handleRequest(req, createContext("create"));
	    	 Assert.assertEquals(errorCode, resp.httpCode);
	    }
	
	@Test
	public void createRemote() {
		CreateRemoteRequest req = new CreateRemoteRequest();
		req.setApi_key("Some API key");
		req.setUrl("TEST Url");
		req.toString();
		String input = new Gson().toJson(req); 
		CreateRemoteResponse resp = new CreateRemoteHandler().handleRequest(req, createContext("create"));	
		resp.toString();
		RemoveRemoteRequest delReq = new RemoveRemoteRequest("TEST Url", "Some API key");
        delReq.toString();
        RemoveRemoteResponse delRes = new RemoveRemoteHandler().handleRequest(delReq, createContext("delete remote url"));
        delRes.toString();
        Assert.assertEquals(200, resp.httpCode);
	}
	@Test
	public void createDuplicate() {
		CreateRemoteRequest req = new CreateRemoteRequest("Dup test", "API key");
		String SAMPLE_INPUT_JSON = new Gson().toJson(req);
		try {
			testSuccessInput(SAMPLE_INPUT_JSON);
			testFailureInput(SAMPLE_INPUT_JSON,422);
			RemoveRemoteRequest delReq = new RemoveRemoteRequest();
			delReq.setApi_key("API key");
			delReq.setUrl("Dup test");
			delReq.toString();
	        RemoveRemoteResponse delRes = new RemoveRemoteHandler().handleRequest(delReq, createContext("delete remote url"));
	        delRes.toString();
		}catch(IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
	@Test
	public void wrongJson() {
		String SAMPLE_INPUT_JSON = "{\"sdsd\": \"e3\"}";
        try {
        	testFailureInput(SAMPLE_INPUT_JSON, 400);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
	}
/*
	public void createRemoteTest(String input, CreateRemoteHandler handler) {
		CreateRemoteRequest request = new Gson().fromJson(input, CreateRemoteRequest.class);
		request.toString();
        CreateRemoteResponse resp = handler.handleRequest(request, createContext("create remote url"));
        resp.toString();
        Assert.assertEquals(200, resp.httpCode);
	}
	public void duplicateTest(String input, CreateRemoteHandler handler) {
		CreateRemoteRequest request = new Gson().fromJson(input, CreateRemoteRequest.class);
        CreateRemoteResponse resp = handler.handleRequest(request, createContext("create remote url"));
        boolean flag = true;
        if(resp.httpCode == 200) flag = false;
        Assert.assertTrue(flag);
	}
	*/
}
