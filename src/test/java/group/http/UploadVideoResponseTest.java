package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.UploadVideoResponse;

public class UploadVideoResponseTest {

	@Test
	public void test() {
		UploadVideoResponse resp = new UploadVideoResponse("hello");
		resp.toString();
		assertTrue(resp.equals(new UploadVideoResponse("hello", 200)));
	}

}
