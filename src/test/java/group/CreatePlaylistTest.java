package group;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreatePlaylistHandler;
import com.cs3733kakistocrat.group.CreateRemoteHandler;
import com.cs3733kakistocrat.group.RemovePlaylistHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
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
	
	@Test
	public void createPlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest("TEST Playlist");
		String input = new Gson().toJson(req); 
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
		
		createPlaylistTest(req, handler);
		duplicateTest(req, handler);
        
        RemovePlaylistRequest delReq = new RemovePlaylistRequest("TEST Playlist");
        delReq.toString();
        RemovePlaylistResponse delRes = new RemovePlaylistHandler().handleRequest(delReq, createContext("delete playlist"));
        delRes.toString();
	}
	
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

}
