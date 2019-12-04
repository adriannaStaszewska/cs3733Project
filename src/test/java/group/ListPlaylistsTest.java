package group;

import java.io.IOException;
import org.junit.Test;

import com.cs3733kakistocrat.group.ListAllPlaylistsHandler;
import com.cs3733kakistocrat.group.http.*;

public class ListPlaylistsTest extends LambdaTest{
	
	@Test
		public void getVideosTest() throws IOException{
		ListAllPlaylistsHandler handler = new ListAllPlaylistsHandler();
		AllPlaylistsResponse resp = handler.handleRequest(null, createContext("list"));
		}
	}
