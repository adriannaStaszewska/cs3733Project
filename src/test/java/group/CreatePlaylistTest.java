package group;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreatePlaylistHandler;
import com.cs3733kakistocrat.group.RemovePlaylistHandler;
import com.cs3733kakistocrat.group.http.CreatePlaylistRequest;
import com.cs3733kakistocrat.group.http.CreatePlaylistResponse;
import com.cs3733kakistocrat.group.http.RemovePlaylistRequest;
import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;
import com.google.gson.Gson;


public class CreatePlaylistTest extends LambdaTest{
	
	@Test
	public void createPlaylist() {
		CreatePlaylistRequest req = new CreatePlaylistRequest("TEST playlist 1");
		String input = new Gson().toJson(req); 
		CreatePlaylistHandler handler = new CreatePlaylistHandler();
    	CreatePlaylistRequest request = new Gson().fromJson(input, CreatePlaylistRequest.class);
       
        CreatePlaylistResponse resp = handler.handleRequest(req, createContext("create playlist"));
        Assert.assertEquals(200, resp.httpCode);
        
        RemovePlaylistRequest delReq = new RemovePlaylistRequest("TEST playlist 1");
        RemovePlaylistResponse delRes = new RemovePlaylistHandler().handleRequest(delReq, createContext("delete playlist"));
	}
	

}
