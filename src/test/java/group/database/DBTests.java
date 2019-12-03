package group.database;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.database.RemotesDAO;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.RemoteSite;
import com.cs3733kakistocrat.group.model.Video;


public class DBTests {
	
	@Test
	public void testCreatePlaylist() {
		
		PlaylistsDAO dao = new PlaylistsDAO();
		
		try {
			dao.getAllPlaylists();
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
			dao.getAllVideos();
			Video video = new Video("Test", "kakistocrat.com", "Me", "This is a sentence.");
			boolean b = dao.addVideo(video);
			Video v2 = dao.getVideo(video.getVideoID());
			assertTrue (dao.deleteVideo(v2));
	    	
		} catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
		
	}

	@Test
	public void testCreateRemotes() {
		RemotesDAO dao = new RemotesDAO();
		
		try {
			dao.getAllRemotes();
	    	RemoteSite rs = new RemoteSite("Some test link");
	    	boolean b = dao.addRemote(rs);
	    	System.out.println("add remote site: " + b);
	    	RemoteSite rs2 = dao.getRemote(rs.getUrl());
	    	System.out.println("Remote:" + rs2.getUrl());
	    	assertTrue (dao.removeRemote(rs2));
	    	
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
	
	@Test
	public void testAddVideoToPlaylist(){
		PlaylistsDAO PlaylistDao = new PlaylistsDAO();
		VideosDAO VideoDao = new VideosDAO();
		VideoToPlaylistDAO VTPDAO = new VideoToPlaylistDAO();
		
		try {
			
			Video video = new Video("Test", "kakistocrat.com", "Me", "This is a sentence.");
			Playlist playlist = new Playlist("This is a test");
			
			boolean b1 = VTPDAO.appendVideoToPlaylist(video, playlist);
			System.out.println("wussup");
			VTPDAO.appendVideoToPlaylist(video, playlist);
			VTPDAO.appendVideoToPlaylist(video, playlist);
			VTPDAO.appendVideoToPlaylist(video, playlist);
			VTPDAO.appendVideoToPlaylist(video, playlist);
			VTPDAO.appendVideoToPlaylist(video, playlist);
			System.out.println("Added Video to Playlist: "+b1);
			VTPDAO.getAllVideos("This is a test");
			assertTrue (VTPDAO.removeVideo(video, playlist,3));
			
		}
		catch(Exception e) {
			fail ("didn't work: " +e.getMessage());
		}
	}
}
