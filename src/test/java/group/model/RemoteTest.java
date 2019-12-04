package group.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cs3733kakistocrat.group.model.RemoteSite;

public class RemoteTest {

	@Test
	public void test() {
		RemoteSite rs= new RemoteSite("www.bizzbuzz.com", "ekc912");
		rs.setApi_key("newkey");
		assertEquals("newkey", rs.getApi_key());
	}
	@Test
	public void SetURL() {
		RemoteSite rs2 = new RemoteSite("www.whoknows.com");
		rs2.setUrl("newURL");
		assertEquals("newURL", rs2.getUrl());
	}

}
