package bibleapi.core;

import org.junit.Test;
import static org.junit.Assert.*;


public class ReferenceTest {
	@Test
	public void testParse() {
		Reference ref = new Reference();
		ref.parse("tob:mc,4,39");
		assertEquals("tob",ref.getVersion());
		assertEquals("mc",ref.getBook());
		assertEquals(new Integer(4),ref.getChapter());
		assertEquals(new Integer(39),ref.getVerset());
	}

	@Test
	public void testToString() {
		Reference ref = new Reference("tob", "mc", 4, 39);
		assertEquals("tob",ref.getVersion());
		assertEquals("mc",ref.getBook());
		assertEquals(new Integer(4),ref.getChapter());
		assertEquals(new Integer(39),ref.getVerset());
		assertEquals("tob:mc,4,39", ref.toString());
	}

}
