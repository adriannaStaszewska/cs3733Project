package group.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.cs3733kakistocrat.group.model.Segment;
import com.cs3733kakistocrat.group.model.Video;

public class SegementTest {
	
	@Test
	public void tests() {
		Segment seg = new Segment("Some url", "Some text", "Some character");
		charTest(seg);
		urlTest(seg);
		textTest(seg);
		
	}
	
	public void charTest(Segment seg) {
		seg.setCharacter("New char");
		assertEquals("New char", seg.getCharacter());
	}
	
	public void urlTest(Segment seg) {
		seg.setUrl("New url");
		assertEquals("New url", seg.getUrl());
	}
	
	public void textTest(Segment seg) {
		seg.setText("New text");
		assertEquals("New text", seg.getText());
	}
	
	

}
