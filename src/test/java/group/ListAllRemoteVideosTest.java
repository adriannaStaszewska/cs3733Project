package group;
import java.io.IOException;

import org.junit.Test;

import com.cs3733kakistocrat.group.ListAllRemoteSegmentsHandler;
import com.cs3733kakistocrat.group.ListAllVideosHandler;
import com.cs3733kakistocrat.group.http.*;


public class ListAllRemoteVideosTest extends LambdaTest{

	@Test
		public void getVideosTest() throws IOException{
		ListAllRemoteSegmentsHandler handler = new ListAllRemoteSegmentsHandler();
		AllRemoteSegmentsResponse resp = handler.handleRequest(null, createContext("list"));
		resp.toString();
	}
}