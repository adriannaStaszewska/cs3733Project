package group;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.AppendVideoToPlaylistHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
import com.cs3733kakistocrat.group.RemoveVideoFromPlaylistHandler;
import com.cs3733kakistocrat.group.http.AppendVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.http.RemoveVideoRequest;
import com.google.gson.Gson;

public class RemoveVideoFromPlaylistTest extends LambdaTest{
	void testSuccessInput(String incomingJson) throws IOException {
		RemoveVideoFromPlaylistHandler handler = new RemoveVideoFromPlaylistHandler();
		RemoveVideoRequest req = new Gson().fromJson(incomingJson, RemoveVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remove"));
		Assert.assertEquals(200, resp.statusCode);
	}

	void testFailureInput(String incomingJson, int errorCode) throws IOException {
		RemoveVideoFromPlaylistHandler handler = new RemoveVideoFromPlaylistHandler();
		RemoveVideoRequest req = new Gson().fromJson(incomingJson, RemoveVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remove"));
		System.out.println("expected: "+errorCode+", but got "+resp.statusCode);
		Assert.assertEquals(errorCode, resp.statusCode);
	}

	@Test
	public void test() {
		AppendVideoRequest appendReq = new AppendVideoRequest("44ba50dd-868d-436e-ac13-fc8cdf885b30", "TEST Playlist 1");
		AppendVideoRequest appendReqRemote = new AppendVideoRequest("father123","TEST Playlist 1", true, "Test char", "Some url", "This is a sentence");
		AppendVideoToPlaylistHandler handler = new AppendVideoToPlaylistHandler();
		handler.handleRequest(appendReq, createContext("append local video"));
		handler.handleRequest(appendReqRemote, createContext("append remote video"));
		RemoveVideoRequest req = new RemoveVideoRequest("44ba50dd-868d-436e-ac13-fc8cdf885b30", "TEST Playlist 1", 0);
		RemoveVideoRequest reqRemote = new RemoveVideoRequest("father123", "TEST Playlist 1", 1);
		RemoveVideoRequest reqFake = new RemoveVideoRequest("i am not real", "TEST Playlist 1", 0);

		String SAMPLE_INPUT_JSON_REMOTE = new Gson().toJson(reqRemote);
		String SAMPLE_INPUT_JSON = new Gson().toJson(req);
		String SAMPLE_INPUT_JSON_FAKE= new Gson().toJson(reqFake);
		
		try {
//			testSuccessInput(SAMPLE_INPUT_JSON_REMOTE);
//			testSuccessInput(SAMPLE_INPUT_JSON);
			testFailureInput(SAMPLE_INPUT_JSON_FAKE,422);
		}catch(IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
