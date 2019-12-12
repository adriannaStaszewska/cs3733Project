package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.Response;

public class ResponseTest {

	@Test
	public void test() {
		Response resp = new Response("hello", 200);
		Response resp2 = new Response ("hi");
		resp.toString();
		assertTrue(resp2.equals(new Response("hi", 200)));
		
	}

}
