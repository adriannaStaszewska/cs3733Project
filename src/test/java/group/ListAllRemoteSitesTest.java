package group;

import java.io.IOException;

import org.junit.Test;

import com.cs3733kakistocrat.group.ListAllRemoteSitesHandler;
import com.cs3733kakistocrat.group.http.AllRemotesResponse;

public class ListAllRemoteSitesTest extends LambdaTest{
	@Test
	public void getVideosTest() throws IOException{
	ListAllRemoteSitesHandler handler = new ListAllRemoteSitesHandler();
	AllRemotesResponse resp = handler.handleRequest(null, createContext("list"));
	resp.toString();
	}

}
