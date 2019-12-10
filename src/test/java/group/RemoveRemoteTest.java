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

public class RemoveRemoteTest extends LambdaTest{
	void testSuccessInput(String incomingJson) throws IOException {
		RemoveRemoteHandler handler = new RemoveRemoteHandler();
		RemoveRemoteRequest req = new Gson().fromJson(incomingJson, RemoveRemoteRequest.class);

		RemoveRemoteResponse resp = handler.handleRequest(req, createContext("remove"));
		Assert.assertEquals(200, resp.statusCode);
	}

	void testFailureInput(String incomingJson, int errorCode) throws IOException {
		RemoveRemoteHandler handler = new RemoveRemoteHandler();
		RemoveRemoteRequest req = new Gson().fromJson(incomingJson, RemoveRemoteRequest.class);

		RemoveRemoteResponse resp = handler.handleRequest(req, createContext("remote"));
		resp.toString();
		System.out.println("expected: "+errorCode+", but got "+resp.statusCode);
		Assert.assertEquals(errorCode, resp.statusCode);
	}

	@Test
	public void deletePlaylistTest() {
		CreateRemoteRequest createReq = new CreateRemoteRequest();
		createReq.setUrl("testing delete");
		createReq.setApi_key("API Key");
		CreateRemoteResponse createResp= new CreateRemoteHandler().handleRequest(createReq, createContext("remove"));
		Assert.assertEquals(200, createResp.httpCode);
		RemoveRemoteRequest req = new RemoveRemoteRequest("wrong testing delete", "API");
		req.setUrl("testing delete");
		req.setApi_key("API Key");
		String SAMPLE_INPUT_JSON = new Gson().toJson(req);
		try {
			testSuccessInput(SAMPLE_INPUT_JSON);
			testFailureInput(SAMPLE_INPUT_JSON,422);
		}catch(IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
}
