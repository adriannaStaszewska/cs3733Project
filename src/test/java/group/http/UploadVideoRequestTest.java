package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.UploadVideoRequest;

public class UploadVideoRequestTest {

	@Test
	public void test() {
		UploadVideoRequest req = new UploadVideoRequest("boop", "beep","Kirk", "im goinng to boop beep around the plannet");
		req.toString();
		req.getCharacter();
		req.getRemote();
		req.getSentence();
		req.getVideoFile();
		req.getVideoName();
		assertFalse(req.getRemote());
		assertTrue(req.equals(new UploadVideoRequest("boop", "beep","Kirk", "im goinng to boop beep around the plannet",false)));
	}

}
