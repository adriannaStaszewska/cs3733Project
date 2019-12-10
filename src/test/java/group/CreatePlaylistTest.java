package group;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreatePlaylistHandler;
import com.cs3733kakistocrat.group.CreateRemoteHandler;
import com.cs3733kakistocrat.group.RemovePlaylistHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
import com.cs3733kakistocrat.group.UploadVideoHandler;
import com.cs3733kakistocrat.group.http.CreatePlaylistRequest;
import com.cs3733kakistocrat.group.http.CreatePlaylistResponse;
import com.cs3733kakistocrat.group.http.CreateRemoteRequest;
import com.cs3733kakistocrat.group.http.CreateRemoteResponse;
import com.cs3733kakistocrat.group.http.RemovePlaylistRequest;
import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;
import com.cs3733kakistocrat.group.http.RemoveRemoteRequest;
import com.cs3733kakistocrat.group.http.RemoveRemoteResponse;
import com.google.gson.Gson;


public class CreatePlaylistTest extends LambdaTest{
	
    void testSuccessInput(String incomingJson) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incomingJson, CreatePlaylistRequest.class);
    	req.toString();
        CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
        resp.toString();
        Assert.assertEquals(200, resp.httpCode);
    }
    
    void testFailureInput(String incomingJson, int errorCode) throws IOException {
    	CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest req = new Gson().fromJson(incomingJson, CreatePlaylistRequest.class);

    	CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create"));
    	 Assert.assertEquals(errorCode, resp.httpCode);
    }
	
	@Test
	public void createPlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest();
		req.setPlaylistName("TEST Playlist");
		String input = new Gson().toJson(req); 
		CreatePlaylistResponse resp = new CreatePlaylistHandler().handleRequest(req, createContext("create"));	
//		createPlaylistTest(req, handler);
//		duplicateTest(req, handler);       
        RemovePlaylistRequest delReq = new RemovePlaylistRequest("TEST Playlist");
        delReq.toString();
        RemovePlaylistResponse delRes = new RemovePlaylistHandler().handleRequest(delReq, createContext("delete playlist"));
        delRes.toString();
//        RemovePlaylistResponse delRes2Fail = new RemovePlaylistHandler().handleRequest(delReq, createContext("delete playlist"));
        Assert.assertEquals(200, resp.httpCode);
	}
	
	@Test
	public void duplicatePlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest("Dup test");
		String SAMPLE_INPUT_JSON = new Gson().toJson(req);
		try {
			testSuccessInput(SAMPLE_INPUT_JSON);
        	testFailureInput(SAMPLE_INPUT_JSON, 422);
        	
        	 RemovePlaylistRequest delReq = new RemovePlaylistRequest("Dup test");
             delReq.toString();
             RemovePlaylistResponse delRes = new RemovePlaylistHandler().handleRequest(delReq, createContext("delete playlist"));
             delRes.toString();
		} catch(IOException ioe) {
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
	public void createPlaylistTest(CreatePlaylistRequest request, CreatePlaylistHandler handler) {
		//CreatePlaylistRequest request = new Gson().fromJson(input, CreatePlaylistRequest.class);
		request.toString();
		//System.out.print(request.toString());
		CreatePlaylistResponse resp = handler.handleRequest(request, createContext("create playlist"));
        resp.toString();
        Assert.assertEquals(200, resp.httpCode);
	}
	public void duplicateTest(CreatePlaylistRequest request, CreatePlaylistHandler handler) {
		//CreatePlaylistRequest request = new Gson().fromJson(input, CreatePlaylistRequest.class);
        CreatePlaylistResponse resp = handler.handleRequest(request, createContext("create duplicate playlist"));
        boolean flag = true;
        if(resp.httpCode == 200) flag = false;
        Assert.assertTrue(flag);
	}
	*/

}
