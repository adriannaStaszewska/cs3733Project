package group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.cs3733kakistocrat.group.DeleteVideoHandler;
import com.cs3733kakistocrat.group.UpdateRemoteStatusHandler;
import com.cs3733kakistocrat.group.http.Response;
import com.cs3733kakistocrat.group.http.UpdateRemoteStatusRequest;

public class UpdateRemoteStatusTest extends LambdaTest{
	
	@Test
	public void Updatetests() {
		UpdateRemoteStatusRequest req = new UpdateRemoteStatusRequest("d1d77dea-61c9-4021-9730-bd682660e63f", true);
		UpdateRemoteStatusHandler handler = new UpdateRemoteStatusHandler();
		successTest(req, handler);
	}
	@Test
	public void invalidParam() {
		UpdateRemoteStatusRequest req = new UpdateRemoteStatusRequest("invalid ID", false);
		UpdateRemoteStatusHandler handler = new UpdateRemoteStatusHandler();
		failTest(req, handler, 422);
	}
	
	public void successTest(UpdateRemoteStatusRequest req, UpdateRemoteStatusHandler handler) {
		Response resp = handler.handleRequest(req, createContext("update remote status"));
		assertEquals(200, resp.httpCode);
	}
	
	public void failTest(UpdateRemoteStatusRequest req,UpdateRemoteStatusHandler handler, int errorCode) {
		handler = new UpdateRemoteStatusHandler();
		Response resp = handler.handleRequest(req, createContext("update remote status"));
		assertEquals(errorCode, resp.httpCode);
	}
	
	
}
