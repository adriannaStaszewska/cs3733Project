package group;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.DeleteVideoHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.DeleteVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.model.Video;
import com.google.gson.Gson;

public class DeleteVideoTest extends LambdaTest {

	void testSuccessInput(String incomingJson) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incomingJson, DeleteVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remove"));
		Assert.assertEquals(200, resp.statusCode);
	}

	void testFailureInput(String incomingJson, int errorCode) throws IOException {
		DeleteVideoHandler handler = new DeleteVideoHandler();
		DeleteVideoRequest req = new Gson().fromJson(incomingJson, DeleteVideoRequest.class);

		DeleteVideoResponse resp = handler.handleRequest(req, createContext("remote"));
		resp.toString();
		System.out.println("expected: " + errorCode + ", but got " + resp.statusCode);
		Assert.assertEquals(errorCode, resp.statusCode);
	}

	@Test
	public void deleteTest() {
		VideosDAO dao = new VideosDAO();
		Video dad = new Video("dad", "papa", "https://3733kakistocrattests.s3.us-east-2.amazonaws.com/videos/DeleteMe.ogg","not MY dad","I love you, son");
		try {
			dao.addVideo(dad, false);
			DeleteVideoRequest req = new DeleteVideoRequest("papa", "dad");
			req.toString();
			String SAMPLE_INPUT_JSON = new Gson().toJson(req);
			System.out.println(SAMPLE_INPUT_JSON);
			try {
				testSuccessInput(SAMPLE_INPUT_JSON);
				testFailureInput(SAMPLE_INPUT_JSON, 422);
			} catch (IOException ioe) {
				Assert.fail("Invalid:" + ioe.getMessage());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
