package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.DeleteVideoResponse;

public class DeletVideoResponseTest {

	@Test
	public void test() {
		DeleteVideoResponse resp = new DeleteVideoResponse("hi",200,"");
		resp.toString();
		assertTrue(resp.equals(new DeleteVideoResponse("hi", 200)));
	}

}
