package group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cs3733kakistocrat.group.AppendVideoToPlaylistHandler;
import com.cs3733kakistocrat.group.http.AppendVideoRequest;
import com.cs3733kakistocrat.group.http.Response;

public class AppendVideoToPlaylist extends LambdaTest {
	
	@Test
	public void test() {
		AppendVideoRequest req = new AppendVideoRequest("44ba50dd-868d-436e-ac13-fc8cdf885b30", "test gateway");
		AppendVideoRequest reqRemote = new AppendVideoRequest("test gateway", true, "Test char", "Some url", "This is a sentence");
		AppendVideoToPlaylistHandler handler = new AppendVideoToPlaylistHandler();
		append(handler, req);
		remoteVidTest(handler, reqRemote);
		//RemoveVideoFromPLaylistRequest delReq = new RemoveVideoFromPLaylistRequest();
		//delete(handler, delReq);
	}

	public void append(AppendVideoToPlaylistHandler handler, AppendVideoRequest req) {
		Response res = handler.handleRequest(req, createContext("append video"));
		System.out.println(res.httpCode);
		assertEquals(res.httpCode, 200);
	}
	
	public void remoteVidTest(AppendVideoToPlaylistHandler handler, AppendVideoRequest req) {
		Response res = handler.handleRequest(req, createContext("append remote video"));
		System.out.println(res.httpCode);
		assertEquals(res.httpCode, 200);
	}
	
	@Test
	public void setName() {
		AppendVideoRequest req = new AppendVideoRequest("44ba50dd-868d-436e-ac13-fc8cdf885b30", "test gateway");
	}
}
