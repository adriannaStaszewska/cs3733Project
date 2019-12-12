package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.AllRemoteSegmentsResponse;

public class AllRemoteSegmentResponse {

	@Test
	public void test() {
		AllRemoteSegmentsResponse resp = new AllRemoteSegmentsResponse(200,"none");
		AllRemoteSegmentsResponse resp2 = new AllRemoteSegmentsResponse(null,200);
		resp2.toString();
		assertTrue(resp.equals(new AllRemoteSegmentsResponse(200,"none")));
	}

}
