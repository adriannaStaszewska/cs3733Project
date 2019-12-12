package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.RemovePlaylistResponse;

public class RemovePlaylistResponseTest {

	@Test
	public void test() {
		RemovePlaylistResponse resp = new RemovePlaylistResponse("name", 200, "none");
		RemovePlaylistResponse resp2 = new RemovePlaylistResponse("name", 400, "none");
		resp2.toString();
		resp.toString();
		assertTrue(resp.equals(new RemovePlaylistResponse("name", 200, "none")));
	}

}
