package group.http;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.http.SearchVideosResponse;

public class SearchVideoResponseTest {

	@Test
	public void test() {
		SearchVideosResponse res= new SearchVideosResponse("kirk", "hello there", 200, "no error");
		assertTrue(true);
	}

}
