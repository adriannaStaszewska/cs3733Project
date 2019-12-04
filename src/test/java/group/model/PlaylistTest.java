package group.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;

public class PlaylistTest {

	@Test
	public void playlistGetName() {
		Playlist p=new Playlist("test");
		assertEquals("test", p.getPlaylistName());
	}
	
	@Test 
	public void playlistSetName() {
		Playlist p2=new Playlist("wrong name");
		p2.setPlaylistName("correct name");
		assertEquals("correct name", p2.getPlaylistName());		
	}
	/*
	@Test
	public void playlistAddVideo() {
		Playlist p3= new Playlist ("videos");
		assertTrue(p3.addVideo(new Video("some id")));
	}
	@Test
	public void playlistRemoveVideo() {
		Playlist p4 = new Playlist("more videos");
		Video v = new Video("some id");
		p4.addVideo(v);
		assertTrue(p4.removeVideo(v));
	}
*/
}
