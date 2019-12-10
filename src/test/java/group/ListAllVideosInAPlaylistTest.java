package group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cs3733kakistocrat.group.ListAllVideosInPlaylistHandler;
import com.cs3733kakistocrat.group.http.AllVideosInPlaylistRequest;
import com.cs3733kakistocrat.group.http.AllVideosResponse;

public class ListAllVideosInAPlaylistTest extends LambdaTest{

	@Test
	public void test() {
		ListAllVideosInPlaylistHandler handler = new ListAllVideosInPlaylistHandler();
		AllVideosInPlaylistRequest testReq = new AllVideosInPlaylistRequest();
		AllVideosInPlaylistRequest req = new AllVideosInPlaylistRequest("test");
		req.setPlaylistName("test gateway");
		AllVideosResponse res = handler.handleRequest(req, createContext("get all videos in a playlist"));
		assertEquals(res.statusCode, 200);
	}
}
