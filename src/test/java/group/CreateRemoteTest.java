package group;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.CreateRemoteHandler;
import com.cs3733kakistocrat.group.RemoveRemoteHandler;
import com.cs3733kakistocrat.group.http.CreateRemoteRequest;
import com.cs3733kakistocrat.group.http.CreateRemoteResponse;
import com.cs3733kakistocrat.group.http.RemoveRemoteRequest;
import com.cs3733kakistocrat.group.http.RemoveRemoteResponse;
import com.google.gson.Gson;

public class CreateRemoteTest extends LambdaTest{
	
	@Test
	public void createRemote() {
		CreateRemoteRequest req = new CreateRemoteRequest("TEST Url", "Some API key");
		String input = new Gson().toJson(req); 
		CreateRemoteHandler handler = new CreateRemoteHandler();
		createRemoteTest(input, handler);
		duplicateTest(input, handler);
        
        RemoveRemoteRequest delReq = new RemoveRemoteRequest("TEST Url", "Some API key");
        delReq.toString();
        RemoveRemoteResponse delRes = new RemoveRemoteHandler().handleRequest(delReq, createContext("delete remote url"));
        delRes.toString();
	}
	
	public void createRemoteTest(String input, CreateRemoteHandler handler) {
		CreateRemoteRequest request = new Gson().fromJson(input, CreateRemoteRequest.class);
		request.toString();
        CreateRemoteResponse resp = handler.handleRequest(request, createContext("create remote url"));
        resp.toString();
        Assert.assertEquals(200, resp.httpCode);
	}
	public void duplicateTest(String input, CreateRemoteHandler handler) {
		CreateRemoteRequest request = new Gson().fromJson(input, CreateRemoteRequest.class);
        CreateRemoteResponse resp = handler.handleRequest(request, createContext("create remote url"));
        boolean flag = true;
        if(resp.httpCode == 200) flag = false;
        Assert.assertTrue(flag);
	}
}
