package group;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.DeleteVideoHandler;
import com.cs3733kakistocrat.group.UploadVideoHandler;
import com.cs3733kakistocrat.group.database.VideosDAO;
import com.cs3733kakistocrat.group.http.DeleteVideoRequest;
import com.cs3733kakistocrat.group.http.DeleteVideoResponse;
import com.cs3733kakistocrat.group.http.UploadVideoRequest;
import com.cs3733kakistocrat.group.http.UploadVideoResponse;
import com.google.gson.Gson;

public class UploadVideoTest extends LambdaTest {
	String vidID;
		
    void testSuccessInput(String incomingJson) throws IOException {
    	UploadVideoHandler handler = new UploadVideoHandler();
    	UploadVideoRequest req = new Gson().fromJson(incomingJson, UploadVideoRequest.class);
       
        UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
    
    void testFailureInput(String incomingJson, int errorCode) throws IOException {
    	UploadVideoHandler handler = new UploadVideoHandler();
    	UploadVideoRequest req = new Gson().fromJson(incomingJson, UploadVideoRequest.class);

    	UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
    	 Assert.assertEquals(errorCode, resp.httpCode);
    }
    @Test
    public void testFailInput() {

    	UploadVideoRequest req = new UploadVideoRequest("me irl", "not base 64 encoded", "McCoy", "Yes, very lonely", false);
    	
    	String SAMPLE_INPUT_STRING =  new Gson().toJson(req);  
        try {
        	testFailureInput(SAMPLE_INPUT_STRING, 400);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    @Test
	public void uploadVideoTest() {
    	File file = new File(getClass().getClassLoader().getResource("sampleVid.ogg").getFile());
    	VideosDAO dao = new VideosDAO();
    	UploadVideoHandler handler = new UploadVideoHandler();
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(file.toPath());
			String encode = java.util.Base64.getEncoder().encodeToString(fileContent);
			UploadVideoRequest req = new UploadVideoRequest("lonely", encode, "McCoy", "Yes, very lonely", false);
	    	UploadVideoResponse resp = handler.handleRequest(req, createContext("upload video"));
	    	try {
				vidID= dao.getAllVideos().get(0).getVideoID();
				Assert.assertEquals(200, resp.httpCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("!!!!");
			e.printStackTrace();
		}
	}


}
