package group.database;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;


public class DBTests {
	
	@Test
	public void testCreatePlaylist() {
		PlaylistsDAO dao = new PlaylistsDAO();
		
		try {
	    	Playlist playlist = new Playlist("Just test");
	    	boolean p = dao.addPlaylist(playlist);
	    	System.out.println("add playlist: " + p);
	    	Playlist p2 = dao.getPlaylist(playlist.getPlaylistName());
	    	System.out.println("Playlist:" + p2.getPlaylistName());
	    	assertTrue (dao.removePlaylist(p2));
	    	
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	@Test 
	public void testCreateVideo() {
		VideosDAO dao = new VideosDAO();
		
		try {
			Video video = new Video("Test", "kakistocrat.com", "Me", "This is a sentence.");
			boolean b = dao.addVideo(video);
			Video v2 = dao.getVideo(video.getVideoID());
			assertTrue (dao.deleteVideo(v2));
	    	
		} catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
		
		
	}

}
