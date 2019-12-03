package group.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.cs3733kakistocrat.group.model.Video;

public class VideoTest {
	
	@Test
	public void videoTests() {
		Video vid1 = new Video("Test name", "SomeID");
		vid1.setVideoID("SomeVidID");
		assertEquals("SomeVidID", vid1.getVideoID());
		Video vid2 = new Video("SomeID2");
		vid2.setRemotely_accessible(true);
		assertTrue(vid2.isRemotely_accessible());
		Video vid3 = new Video("Video 1", "Test char", "Hello world");
		assertEquals("Video 1", vid3.getName());
		vid3.setCharacter("New char");
		vid3.setName("New name");
		vid3.setUrl("my url");
		vid3.setSentence("New sentence");
		assertEquals("New name", vid3.getName());
		assertEquals("New char", vid3.getCharacter());
	}

}
