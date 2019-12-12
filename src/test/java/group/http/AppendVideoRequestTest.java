package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.AppendVideoRequest;

public class AppendVideoRequestTest {

	@Test
	public void test() {
		AppendVideoRequest req = new AppendVideoRequest("Test",true, "spock", "spock.com", "this is a sentence");
		AppendVideoRequest req2 = new AppendVideoRequest("someID", "somePlaylist", true);
		req.getCharacter();
		req.getPlaylistName();
		req.getUrl();
		req.getText();
		req2.toString();
		assertTrue(req.equals(new AppendVideoRequest("Test",true, "spock", "spock.com", "this is a sentence")));
	}

}
