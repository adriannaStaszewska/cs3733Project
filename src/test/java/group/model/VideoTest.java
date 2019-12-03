package group.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.cs3733kakistocrat.group.model.Video;

public class VideoTest {
	
	
	@Test
	public void videoID() {
		Video vid1 = new Video("Test name", "SomeID");
		vid1.setVideoID("SomeVidID");
		assertEquals("SomeVidID", vid1.getVideoID());		
	}
	
	@Test
	public void videoRemotelyAccessible() {
		Video vid2 = new Video("SomeID2");
		vid2.setRemotely_accessible(true);
		assertTrue(vid2.isRemotely_accessible());
	}

	@Test
	public void videoGetName() {
	Video vid3 = new Video("Video 1", "Test char", "Hello world");
	assertEquals("Video 1", vid3.getName());
	}
	
	@Test
	public void videoSetName() {
		Video vid4 = new Video("Video 4", "Spoccoy", "Who knows");
		vid4.setName("not Video 4");
		assertEquals("not Video 4", vid4.getName());
	}
	
	@Test
	public void videoSetCharacter() {
		Video vid3 = new Video("Video 1", "Test char", "Hello world");
		vid3.setCharacter("New char");
		assertEquals("New char", vid3.getCharacter());
	}

}
