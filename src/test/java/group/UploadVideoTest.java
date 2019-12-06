package group;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

import com.cs3733kakistocrat.group.UploadVideoHandler;
import com.cs3733kakistocrat.group.http.UploadVideoRequest;
import com.cs3733kakistocrat.group.http.UploadVideoResponse;
import com.google.gson.Gson;

public class UploadVideoTest extends LambdaTest {
		
    void testSuccessInput(String name, String character, String sentence) throws IOException {
    	File file = new File(getClass().getClassLoader().getResource("sampleVid.ogg").getFile());
    	UploadVideoHandler handler = new UploadVideoHandler();
		byte[] fileContent = Files.readAllBytes(file.toPath());
		String encode = java.util.Base64.getEncoder().encodeToString(fileContent);
    	UploadVideoRequest req = new UploadVideoRequest(name, encode, character, sentence);
        UploadVideoResponse resp = handler.handleRequest(req, createContext("create"));
        Assert.assertEquals(200, resp.httpCode);
    }
    @Test
	public void uploadVideoTest() {
    	File file = new File(getClass().getClassLoader().getResource("sampleVid.ogg").getFile());
    	UploadVideoHandler handler = new UploadVideoHandler();
		byte[] fileContent;
		try {
			fileContent = Files.readAllBytes(file.toPath());
			String encode = java.util.Base64.getEncoder().encodeToString(fileContent);
			UploadVideoRequest req = new UploadVideoRequest("me irl", encode, "McCoy", "Yes, very lonely", false);
	    	UploadVideoResponse resp = handler.handleRequest(req, createContext("upload video"));
			Assert.assertEquals(200, resp.httpCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("!!!!");
			e.printStackTrace();
		}
	}

	public void duplicateTest(UploadVideoRequest req, UploadVideoHandler handler) {
		UploadVideoResponse resp = handler.handleRequest(req, createContext("create duplicate playlist"));
		boolean flag = true;
		System.out.println(resp.httpCode);
		if (resp.httpCode == 200)
			flag = false;
		Assert.assertTrue(flag);
	}

}
