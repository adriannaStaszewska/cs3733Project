package group;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.AppendVideoToPlaylistHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
import com.cs3733kakistocrat.group.RemoveVideoFromPlaylistHandler;
import com.cs3733kakistocrat.group.database.PlaylistsDAO;
import com.cs3733kakistocrat.group.database.VideoToPlaylistDAO;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.AppendVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.http.RemoveVideoRequest;
import com.cs3733kakistocrat.group.model.Playlist;
import com.cs3733kakistocrat.group.model.Video;
import com.google.gson.Gson;

public class RemoveVideoFromPlaylistTest extends LambdaTest {

	void testSuccessInput(String incomingJson) throws IOException {
		RemoveVideoFromPlaylistHandler handler = new RemoveVideoFromPlaylistHandler();
		RemoveVideoRequest req = new Gson().fromJson(incomingJson, RemoveVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remove"));
		Assert.assertEquals(200, resp.statusCode);
	}

	void testFailureInput(String incomingJson, int errorCode) throws IOException {
		RemoveVideoFromPlaylistHandler handler = new RemoveVideoFromPlaylistHandler();
		RemoveVideoRequest req = new Gson().fromJson(incomingJson, RemoveVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remove"));
		System.out.println("expected: " + errorCode + ", but got " + resp.statusCode);
		Assert.assertEquals(errorCode, resp.statusCode);
	}

	@Test
	public void test() {
		PlaylistsDAO PDao = new PlaylistsDAO();
		VideoToPlaylistDAO VTPDao = new VideoToPlaylistDAO();
		VideosDAO videoDAO = new VideosDAO();
		Playlist test = new Playlist("test");
		Video vid = new Video("father123", "where is papa?", "idk.com", "not my dad", "I love you", false);

		try {
			PDao.addPlaylist(test);
			videoDAO.addVideo(vid, false);
			VTPDao.appendVideoToPlaylist(vid, test);
			RemoveVideoRequest req = new RemoveVideoRequest("father123", "test", 1);
			String SAMPLE_INPUT_JSON = new Gson().toJson(req);
			
			testSuccessInput(SAMPLE_INPUT_JSON);
			testFailureInput(SAMPLE_INPUT_JSON, 422);
			PDao.removePlaylist(test);
			videoDAO.deleteVideo(vid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
