package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.AllRemotesResponse;

public class AllRemoteResponseTest {

	@Test
	public void test() {
		AllRemotesResponse resp = new AllRemotesResponse(200, "none");
		resp.toString();
		assertTrue(resp.equals(new AllRemotesResponse(200, "none")));
	}

}
