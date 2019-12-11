package group;

import java.io.IOException;

import org.junit.Test;

import com.cs3733kakistocrat.group.ListAllVideosHandler;
import com.cs3733kakistocrat.group.http.*;


public class ListVideosTest extends LambdaTest{

	@Test
		public void getVideosTest() throws IOException{
		ListAllVideosHandler handler = new ListAllVideosHandler();
		AllVideosResponse resp = handler.handleRequest(null, createContext("list"));
		resp.toString();
		AllVideosResponse codeResp = new AllVideosResponse(404, null);
	}
}
