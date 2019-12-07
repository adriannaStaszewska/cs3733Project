package group;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreatePlaylistHandler;
import com.cs3733kakistocrat.group.RemovePlaylistHandler;
import com.cs3733kakistocrat.group.http.CreatePlaylistRequest;
import com.cs3733kakistocrat.group.http.CreatePlaylistResponse;
import com.cs3733kakistocrat.group.http.RemovePlaylistRequest;
import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;
import com.google.gson.Gson;

public class DeletePlaylistTest extends LambdaTest{

	void testSuccessInput(String incomingJson) throws IOException {
		RemovePlaylistHandler handler = new RemovePlaylistHandler();
		RemovePlaylistRequest req = new Gson().fromJson(incomingJson, RemovePlaylistRequest.class);

		RemovePlaylistResponse resp = handler.handleRequest(req, createContext("remove"));
		Assert.assertEquals(200, resp.statusCode);
	}

	void testFailureInput(String incomingJson, int errorCode) throws IOException {
		RemovePlaylistHandler handler = new RemovePlaylistHandler();
		RemovePlaylistRequest req = new Gson().fromJson(incomingJson, RemovePlaylistRequest.class);

		RemovePlaylistResponse resp = handler.handleRequest(req, createContext("remove"));
		System.out.println("expected: "+errorCode+", but got "+resp.statusCode);
		Assert.assertEquals(errorCode, resp.statusCode);
	}

	@Test
	public void deletePlaylistTest() {
		CreatePlaylistRequest createReq = new CreatePlaylistRequest("testing delete");
		CreatePlaylistResponse createResp= new CreatePlaylistHandler().handleRequest(createReq, createContext("remove"));
		Assert.assertEquals(200, createResp.httpCode);
		RemovePlaylistRequest req = new RemovePlaylistRequest("testing delete");
		String SAMPLE_INPUT_JSON = new Gson().toJson(req);
		try {
			testSuccessInput(SAMPLE_INPUT_JSON);
			testFailureInput(SAMPLE_INPUT_JSON,422);
		}catch(IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
//	@Test
//	public void wrongJson() {
//		String SAMPLE_INPUT_JSON = "{\"sdsd\": \"e3\"}";
//		try {
//			testFailureInput(SAMPLE_INPUT_JSON, 400);
//		}catch(IOException ioe) {
//        	Assert.fail("Invalid:" + ioe.getMessage());
//		}
//	}

}
