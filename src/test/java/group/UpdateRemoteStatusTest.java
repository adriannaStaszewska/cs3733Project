package group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cs3733kakistocrat.group.UpdateRemoteStatusHandler;
import com.cs3733kakistocrat.group.http.Response;
import com.cs3733kakistocrat.group.http.UpdateRemoteStatusRequest;

public class UpdateRemoteStatusTest extends LambdaTest{
	
	@Test
	public void test() {
		UpdateRemoteStatusRequest req = new UpdateRemoteStatusRequest("d1d77dea-61c9-4021-9730-bd682660e63f", true);
		UpdateRemoteStatusHandler handler = new UpdateRemoteStatusHandler();
		Response resp = handler.handleRequest(req, createContext("update remote status"));
		assertEquals(200, resp.httpCode);
	}
	
	
}
