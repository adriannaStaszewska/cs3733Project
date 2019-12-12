package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.AllPlaylistsResponse;

public class AllPlaylistResponseTest {

	@Test
	public void test() {
		AllPlaylistsResponse resp = new AllPlaylistsResponse(200, "none");
		AllPlaylistsResponse resp2 = new AllPlaylistsResponse(null, 200);
		resp2.toString();
		assertTrue(resp.equals(resp = new AllPlaylistsResponse(200, "none")));
	}

}
